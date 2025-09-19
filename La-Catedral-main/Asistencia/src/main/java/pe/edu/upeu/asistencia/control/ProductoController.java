package pe.edu.upeu.asistencia.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.modelo.Producto;
import pe.edu.upeu.asistencia.servicio.ProductoServicioI;

@Controller
public class ProductoController {
    @Autowired
    ProductoServicioI psi;

    @FXML
    private TextField txtCodigoProducto, txtNombre, txtUnidadDeMedida;
    @FXML
    private Spinner<Integer> spnCantidad;

    @FXML
    private TableView<Producto> tableView;
    ObservableList<Producto> productos;
    private TableColumn<Producto, String> codProdColumn, nombreColumn, cantidadColumn, unidadMedidaColumn;

    private TableColumn<Producto, Void> opcColumn;
    private int indexEdit = -1;

    @FXML
    private void initialize() {
        spnCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999, 0));
        spnCantidad.setEditable(true);

        definirColumnas();
        listar();
    }

    public void definirColumnas() {
        codProdColumn = new TableColumn<>("Cod. Producto");
        nombreColumn = new TableColumn<>("Nombre");
        cantidadColumn = new TableColumn<>("Cantidad");
        unidadMedidaColumn = new TableColumn<>("Unidad de Medida");
        opcColumn = new TableColumn<>("Opciones");
        opcColumn.setMinWidth(180);
        tableView.getColumns().addAll(codProdColumn, nombreColumn, cantidadColumn, unidadMedidaColumn, opcColumn);
    }

    private void listar() {
        codProdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigoProducto()));
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        cantidadColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));
        unidadMedidaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnidadDeMedida()));

        addActionButtonsToTable();
        productos = FXCollections.observableArrayList(psi.findAll());

        tableView.setItems(productos);
    }

    private void addActionButtonsToTable() {
        Callback<TableColumn<Producto, Void>, TableCell<Producto, Void>>
                cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setOnAction(event -> {
                    Producto p = getTableView().getItems().get(getIndex());
                    editar(p, getIndex());
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

    @FXML
    public void editar(Producto p, int index) {
        txtCodigoProducto.setText(p.getCodigoProducto());
        txtNombre.setText(p.getNombre());
        spnCantidad.getValueFactory().setValue(p.getCantidad());
        txtUnidadDeMedida.setText(p.getUnidadDeMedida());
        indexEdit = index;
    }

    @FXML
    public void eliminar(int index) {
        psi.delete(index);
        listar();
    }

    public void limpiarFormulario() {
        txtCodigoProducto.clear();
        txtNombre.clear();
        spnCantidad.getValueFactory().setValue(0);
        txtUnidadDeMedida.clear();
        indexEdit = -1;
    }

    @FXML
    public void registrar() {
        Producto producto = new Producto();
        producto.setCodigoProducto(txtCodigoProducto.getText());
        producto.setNombre(txtNombre.getText());
        producto.setCantidad(spnCantidad.getValue());
        producto.setUnidadDeMedida(txtUnidadDeMedida.getText());

        if (indexEdit != -1) {
            psi.update(producto, indexEdit);
            indexEdit = -1;
        } else {
            psi.save(producto);
        }
        limpiarFormulario();
        listar();
    }
}
