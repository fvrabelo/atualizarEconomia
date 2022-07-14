package Principal;

import java.io.IOException;
import java.util.ArrayList;

public class Home {

    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");

        LerGravarCSV leituraGravacao = new LerGravarCSV();
        leituraGravacao.gravar();

        AtualizandoEconomia aE = new AtualizandoEconomia();

        try{
            aE.Gerar();
        }catch (Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
}