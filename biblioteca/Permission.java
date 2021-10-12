
public class Permission {
    private Conta current_user;

    public Permission(Conta current_user) {
        this.current_user = current_user;
    }

    public boolean canAcess(String command) {
        if (current_user.eh_bibliotecario()) {
            return true;
        } else {
            return !Utils.stringEmArray(Commands.SOMENTE_BIBLIOTECARIO, command);
        }
    }

    public Conta getCurrentUser() {
        return this.current_user;
    }

    public void setCurrentUser(Conta current_user) {
        this.current_user = current_user;
    }
}