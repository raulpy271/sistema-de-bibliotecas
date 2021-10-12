
public class Permission {
    private Conta current_user;

    public Permission(Conta current_user) {
        this.current_user = current_user;
    }

    public boolean canAcess(String command) {
        return true;
    }

    public Conta getCurrentUser() {
        return this.current_user;
    }

    public void setCurrentUser(Conta current_user) {
        this.current_user = current_user;
    }
}