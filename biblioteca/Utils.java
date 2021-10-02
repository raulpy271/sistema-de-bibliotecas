
class Utils {
    public static int converteMilisegundosParaDias(long milissegundos) {
        int fator_conversao = 86400000;
        return (int) (milissegundos / fator_conversao);
    }
}
