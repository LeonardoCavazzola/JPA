package vo;

import java.time.LocalDateTime;

public class RelatorioVo {

    private String nomeProduto;
    private Long quantidade;
    private LocalDateTime dateTime;

    public RelatorioVo(String nomeProduto, Long quantidade, LocalDateTime dateTime) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.dateTime = dateTime;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "RelatorioVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                ", dateTime=" + dateTime +
                '}';
    }
}
