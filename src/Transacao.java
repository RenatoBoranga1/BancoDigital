import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private String tipo;
    private double valor;
    private String descricao;
    private LocalDateTime dataHora;

    public Transacao(String tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "[" + dataHora.format(formatter) + "] " + tipo + " - R$ " + String.format("%.2f", valor) + " - " + descricao;
    }
}
