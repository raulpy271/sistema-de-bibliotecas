package biblioteca;

public class Commands {

    // Somente bibliotecário
    public static final String ADD_LIVRO = "add_livro";
    public static final String EDIT_LIVRO = "edit_livro";
    public static final String ADD_ITEM = "add_item";
    public static final String ADD_USER = "add_user";
    public static final String REMOVE_USER = "remove_user";
    public static final String WHO_DO_CHECKOUT = "who_do_checkout";
    public static final String CHECK_ATRASO = "check_atraso";
    public static final String ITENS_OF_THIS_USER = "itens_of_this_user";
    public static final String REMOVE_ITEM = "remove_item";
    public static final String REMOVE_LIVRO = "remove_livro";

    public static final String CHECKOUT = "checkout";
    public static final String DEVOLVER = "devolver";
    public static final String EXIT = "exit";
    public static final String SEARCH = "search";
    public static final String LOGIN = "login";
    public static final String HELP = "help";
    public static final String LOGGED_INFO = "logged_info";
    public static final String RENOVAR = "renovar";
    public static final String RESERVAR = "reservar";

    public static final String[] SOMENTE_BIBLIOTECARIO = {
        WHO_DO_CHECKOUT,
        CHECK_ATRASO,
        ITENS_OF_THIS_USER,
        ADD_USER,
        REMOVE_USER,
        ADD_LIVRO,
        ADD_ITEM,
        EDIT_LIVRO,
        REMOVE_ITEM,
        REMOVE_LIVRO,
    };
}
