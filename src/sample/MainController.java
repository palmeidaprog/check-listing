package sample;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

public class MainController {

    //--Singleton design---------------------------------------------------------
    private volatile static MainController instance = null;
    private MainController() { }
    public static synchronized MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    //----------------------------------------------------------------------------
}
