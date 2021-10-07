
class Utils {
    public static int converteMilisegundosParaDias(long milissegundos) {
        int fator_conversao = 86400000;
        return (int) (milissegundos / fator_conversao);
    }

    public static boolean stringEmArray(String[] arr, String para_buscar) {
        for (String element: arr) {
            if (element != null && element.contains(para_buscar)) {
                return true;
            }
        }
        return false;
    }
}
