import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean contemNoInicio(String string_completa, String string_no_inicio){
        int tamanho_da_segunda_str = string_no_inicio.length();
        if (string_completa.length() >= tamanho_da_segunda_str) {
            String parte_da_str_completa = string_completa.substring(0, tamanho_da_segunda_str);
            return parte_da_str_completa.equals(string_no_inicio);

        } else {
            return false;
        }
    }

    public static String[] divideArgumentos(String input) {
        int MAX_ARGS = 10;
        int counter = 0;
        String[] result = new String[MAX_ARGS];
        Pattern regex_para_dividir_string = Pattern.compile("\\w+|\"(\\w|\\s)+\"");
        Matcher matcher = regex_para_dividir_string.matcher(input);
        while (matcher.find() && counter < MAX_ARGS) {
            result[counter] = matcher.group().replaceAll("\"", "");
            counter++;
        };
        return result;
    }
}
