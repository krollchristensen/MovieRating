package org.example.movierating;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.movierating.model.MovieRating;
import org.example.movierating.model.MovieRatingManager;

import java.util.ArrayList;
/*
MovieRatingController:
Dette er JavaFX-controlleren, som styrer interaktionen mellem brugergrænsefladen (FXML-filen)
og applikationslogikken (MovieRatingManager).
I controlleren kaldes metoderne i MovieRatingManager til at udføre handlinger baseret på
brugerens input eller begivenheder i brugergrænsefladen.
Controlleren opdaterer også brugergrænsefladen, f.eks. ved at vise data fra databasen
i en tabel eller opdatere et tekstfelt med brugerens input.
 */
public class MovieRatingController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Slider sliRating;

//    public MovieRatingController() {
//        tabMovies.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if(mouseEvent.getClickCount()==2){
//                    System.out.println("Dobbelt klik");
//                }
//            }
//        });
//
//
//    }

    @FXML
    private TableView<MovieRating> tabMovies;
    @FXML
    private TableColumn<MovieRating, String> attribut1Col;
    @FXML
    private TableColumn<MovieRating, String> attribut2Col;
    @FXML
    private TableColumn<MovieRating, Number> attribut3Col;

    @FXML
    private Tab tabUpdate;
    @FXML
    private TabPane tabpane;

    @FXML
    private Button btnUpdate;

    @FXML
    private Slider sliUpdateRating;

    @FXML
    private TextArea txtUpdateDescription;
    @FXML
    private TextField txtUpdateTitle;

    @FXML
    protected void onBtnSaveClick() {

        MovieRatingManager mrm = new MovieRatingManager();

        mrm.create(txtTitle.getText(), txtDescription.getText(), (int) sliRating.getValue());

    }
@FXML
    protected void onTabMoviesClick() {

        MovieRatingManager mrm = new MovieRatingManager();
        ArrayList<MovieRating> displayList = mrm.display();


        attribut1Col = new TableColumn<>("Titel");
        attribut1Col.setCellValueFactory(new PropertyValueFactory<>("title"));

        attribut2Col = new TableColumn<>("Beskrivelse");
        attribut2Col.setCellValueFactory(new PropertyValueFactory<>("Description"));

        attribut3Col = new TableColumn<>("Bedømmelse");
        attribut3Col.setCellValueFactory(new PropertyValueFactory<>("Rating"));

        tabMovies.getColumns().clear();
        tabMovies.getItems().clear();

        tabMovies.getColumns().add(attribut1Col);
        tabMovies.getColumns().add(attribut2Col);
        tabMovies.getColumns().add(attribut3Col);
        tabMovies.getItems().addAll(displayList);
    }

    @FXML
    protected void onMoviesSelectedClick() {

        if(tabMovies.getSelectionModel().getSelectedItem()!=null)
        {   btnUpdate.setDisable(false);
            System.out.println(tabMovies.getSelectionModel().getSelectedItem().toString());

        }

    }

    @FXML
    protected void beforeMovieUpdate() {

        if(tabMovies.getSelectionModel().getSelectedItem()!=null)
        {   btnUpdate.setDisable(true);
            MovieRating mr = tabMovies.getSelectionModel().getSelectedItem();
            tabpane.getSelectionModel().select(tabUpdate);

            tabUpdate.setDisable(false);

            txtUpdateTitle.setText(mr.getTitle());
            txtUpdateDescription.setText(mr.getDescription());
            sliUpdateRating.setValue(mr.getRating());
        }

    }

    @FXML
    protected void onMovieUpdate() {

        MovieRatingManager movieRatingManager = new MovieRatingManager();
        movieRatingManager.update(new MovieRating(txtUpdateTitle.getText(), txtUpdateDescription.getText(), (int) sliUpdateRating.getValue()));

        tabpane.getSelectionModel().select(1);
        tabUpdate.setDisable(true);


    }




    }