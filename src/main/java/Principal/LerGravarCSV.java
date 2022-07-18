package Principal;


import java.util.ArrayList;
import java.io.IOException;
import javax.swing.JFileChooser;

public class LerGravarCSV {

    static final ArrayList<String> ANO = new ArrayList<String>();
    static final ArrayList<String> MATRICULA = new ArrayList<String>();
    static final ArrayList<String> ORDEMSERVICO = new ArrayList<String>();
    static final ArrayList<String> RESIDENCIAL = new ArrayList<String>();
    static final ArrayList<String> COMERCIAL = new ArrayList<String>();
    static String ano;
    static String ordemServico;
    static String numMatricula;
    static String residencial;
    static String comercial;



    //escolhe CSV
    public String escolherArquivos() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Escolha o arquivo .CSV com Ano, Número da O.S. e Número da Ligação ");
        fc.setDialogType(fc.OPEN_DIALOG);
        fc.setApproveButtonText("OK");
        fc.setFileSelectionMode(fc.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);
        int resultado = fc.showOpenDialog(fc);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            System.exit(1);
        }
        String arquivo = fc.getSelectedFile().getPath();

        return arquivo;
    }

    //escolhe CSV
    public void gravar() {

        try {
            TextFile arquivo = new TextFile(escolherArquivos());
            arquivo.openTextFile();
            String[] vDados;

            while (arquivo.next()) {
                //le linha
                String Linha = arquivo.readLine();
                //separa linha por caracter ';'
                vDados = Linha.split(";", -1);
                //parametro p armazenar posicao zero da linha lida, com separacao trim das demais. No caso, forma primeira coluna
                ano = vDados[0].trim();
                //Array com valor vindo do parametro
                ANO.add(ano);
                System.out.println("ANO: " + ano);

                numMatricula = vDados[1].trim();
                MATRICULA.add(numMatricula);
                System.out.println("Nº O.S.: " + numMatricula);

                ordemServico = vDados[2].trim();
                ORDEMSERVICO.add(ordemServico);
                System.out.println("Nº O.S.: " + ordemServico);

                residencial = vDados[3].trim();
                RESIDENCIAL.add(residencial);
                System.out.println("QTD RES.: " + residencial);

                comercial = vDados[4].trim();
                COMERCIAL.add(comercial);
                System.out.println("QTD COM.: " + comercial);


            }


        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }


    }


}
