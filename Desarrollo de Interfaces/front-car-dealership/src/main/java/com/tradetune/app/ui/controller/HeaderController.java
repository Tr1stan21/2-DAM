package com.tradetune.app.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HeaderController {

    @FXML
    private Button btnLogOut;

    @FXML
    private Label email;

    @FXML
    private Label section;

    /**
     * initialize()
     * Inicializa el estado visual del header y deja el componente preparado para recibir datos externos.
     *
     * setSection(String sectionText)
     * Recibe y establece el nombre de la sección actual en el header.
     *
     * setEmail(String emailText)
     * Recibe y muestra el email del usuario autenticado.
     *
     * updateHeaderData(String sectionText, String emailText)
     * Actualiza de forma conjunta los datos dinámicos del header (sección y email).
     *
     * onLogoutAction()
     * Gestiona la acción de cierre de sesión al pulsar el botón de logout.
     *
     * clearHeader()
     * Limpia los datos visibles del header cuando se invalida la sesión o cambia el contexto.
     *
     * disableLogoutButton(boolean disable)
     * Habilita o deshabilita el botón de logout según el estado de la aplicación.
     *
     * applyHeaderState()
     * Aplica ajustes visuales o de estado del header tras recibir nuevos datos (por ejemplo, refresco de labels).
     */
}
