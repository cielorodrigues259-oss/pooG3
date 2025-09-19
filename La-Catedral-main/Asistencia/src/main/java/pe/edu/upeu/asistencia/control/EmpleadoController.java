package pe.edu.upeu.asistencia.control;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.dto.PersonaDto;
import pe.edu.upeu.asistencia.modelo.Empleado;
import pe.edu.upeu.asistencia.servicio.EmpleadoServicioI;

import java.util.Set;

@Controller
public class EmpleadoController {
    @FXML
    private TextField txtDni, txtNombres, txtApellidos, txtPuesto;

    ObservableList<Empleado> empleados;
    @FXML
    TableView<Empleado> tableRegPart;
    private Validator validator;

    @Autowired
    EmpleadoServicioI esi;

    TableColumn<Empleado, Void> opcColumn;
    TableColumn<Empleado, String> dniColumn, nombreColumn, apellidosColumn, puestoColumn;
    private int indexEdit = -1;

    @FXML
    public void initialize() {
        //--------------------inicio validacion--------------
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        // Limpiar estilos de error al escribir en los campos
        txtDni.textProperty().addListener((obs, oldV, newV) -> clearErrorStyle(txtDni));
        txtNombres.textProperty().addListener((obs, oldV, newV) -> clearErrorStyle(txtNombres));
        txtApellidos.textProperty().addListener((obs, oldV, newV) -> clearErrorStyle(txtApellidos));
        txtPuesto.textProperty().addListener((obs, oldV, newV) -> clearErrorStyle(txtPuesto));
        //--------------------fin validacion--------------
        definirColumnas();
        listarEmpleados();
    }

    private void listarEmpleados() {
        dniColumn.setCellValueFactory(cellData -> cellData.getValue().getDni());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().getApellidos());
        puestoColumn.setCellValueFactory(cellData -> cellData.getValue().getPuesto());
        addActionButtonsToTable();
        empleados = FXCollections.observableArrayList(esi.findAll());
        tableRegPart.setItems(empleados);
    }

    @FXML
    public void buscarPorDni() {
        PersonaDto personaDto = esi.findByDni(txtDni.getText());
        if (personaDto != null) {
            txtNombres.setText(personaDto.getNombre());
            txtApellidos.setText(personaDto.getApellidoPaterno() + " " + personaDto.getApellidoMaterno());
        } else {
            txtNombres.setText("");
            txtApellidos.setText("");
        }
    }

    public void limpiarFormulario() {
        txtDni.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtPuesto.clear();
        indexEdit = -1;
    }

    public void limpiarEstilosFormulario() {
        clearErrorStyle(txtDni);
        clearErrorStyle(txtNombres);
        clearErrorStyle(txtApellidos);
        clearErrorStyle(txtPuesto);
    }

    @FXML
    public void registrar() {
        limpiarEstilosFormulario();

        Empleado e = new Empleado();
        e.setDni(new SimpleStringProperty(txtDni.getText()));
        e.setNombre(new SimpleStringProperty(txtNombres.getText()));
        e.setApellidos(new SimpleStringProperty(txtApellidos.getText()));
        e.setPuesto(new SimpleStringProperty(txtPuesto.getText()));

        Set<ConstraintViolation<Empleado>> violations = validator.validate(e);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Empleado> violation : violations) {
                String fieldName = violation.getPropertyPath().toString();
                switch (fieldName) {
                    case "dni":
                        setErrorStyle(txtDni);
                        txtDni.requestFocus();
                        break;
                    case "nombre":
                        setErrorStyle(txtNombres);
                        txtNombres.requestFocus();
                        break;
                    case "apellidos":
                        setErrorStyle(txtApellidos);
                        txtApellidos.requestFocus();
                        break;
                    case "puesto":
                        setErrorStyle(txtPuesto);
                        txtPuesto.requestFocus();
                        break;
                }
            }
        } else {
            if (indexEdit != -1) {
                esi.update(e, indexEdit);
            } else {
                esi.save(e);
            }
            limpiarFormulario();
            listarEmpleados();
        }
    }

    @FXML
    public void editar(Empleado e, int index) {
        txtDni.setText(e.getDni().getValue());
        txtNombres.setText(e.getNombre().getValue());
        txtApellidos.setText(e.getApellidos().getValue());
        txtPuesto.setText(e.getPuesto().getValue());
        indexEdit = index;
    }

    @FXML
    public void eliminar(int index) {
        esi.delete(index);
        listarEmpleados();
    }

    public void definirColumnas() {
        dniColumn = new TableColumn<>("DNI");
        nombreColumn = new TableColumn<>("Nombre");
        apellidosColumn = new TableColumn<>("Apellidos");
        apellidosColumn.setMinWidth(200);
        puestoColumn = new TableColumn<>("Puesto");
        opcColumn = new TableColumn<>("Opciones");
        opcColumn.setMinWidth(200);
        tableRegPart.getColumns().addAll(dniColumn, nombreColumn, apellidosColumn, puestoColumn, opcColumn);
    }

    private void addActionButtonsToTable() {
        Callback<TableColumn<Empleado, Void>, TableCell<Empleado, Void>>
                cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setOnAction(event -> {
                    Empleado e = getTableView().getItems().get(getIndex());
                    editar(e, getIndex());
                });
                deleteButton.getStyleClass().setAll("button", "delete-button");
                deleteButton.setOnAction(event -> {
                    eliminar(getIndex());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    buttons.setSpacing(10);
                    setGraphic(buttons);
                }
            }
        };
        opcColumn.setCellFactory(cellFactory);
    }

    private void clearErrorStyle(TextField field) {
        field.getStyleClass().remove("error-field");
    }

    private void setErrorStyle(TextField field) {
        if (!field.getStyleClass().contains("error-field")) {
            field.getStyleClass().add("error-field");
        }
    }
}
