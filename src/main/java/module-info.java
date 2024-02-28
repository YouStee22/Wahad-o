module com.example.wachadlo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wachadlo to javafx.fxml;
    exports com.example.wachadlo;
}