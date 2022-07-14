package Principal;


import jdk.swing.interop.SwingInterOpUtils;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.IOException;
import java.security.Key;
import java.security.Principal.*;

import static Principal.LerGravarCSV.*;

public class AtualizandoEconomia {

    public String enderecoGSSHomologLOGIN = "https://hml-gss.aegea.com.br/ords/portal/aegea/r/portal/selecionar-unidade";
    public String enderecoGSSHomolog2 = "https://hml-gss.aegea.com.br";
    public String enderecoGSSProd = "https://gss.aegea.com.br";
    public String enderecoGSS = enderecoGSSProd;
    public String enderecoAcatamento = enderecoGSS + "/ords/prolagos/aegea/r/gss101/ate3100s1?clear=257&session=";
    public String session;
    public String valorRes;
//    public int valorResInt = Integer.parseInt(valorRes);
    public String valorInd;
//    public int valorIndInt = Integer.parseInt(valorInd);
    public String valorPub;
//    public int valorPubInt = Integer.parseInt(valorPub);
    public String valorCom;
//    public int valorComInt = Integer.parseInt(valorCom);

    public void Gerar() throws InterruptedException, IOException {

        System.out.println("=========================================================================");
        System.out.println("========================== INICIANDO ATIVIDADE ==========================");
        System.out.println("======================== ATUALIZADOR DE ECONOMIA ========================");
        System.out.println("=========================================================================");
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");


        System.out.println("iniciando janela maximizada");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");


        //url inicial é enderecoGSSHomologLOGIN, nao mexe aqui pq vai entrar na tela inicial p homologar
        System.out.println("passando URL");
        WebDriver browser = new ChromeDriver(options);
        browser.get(enderecoGSSHomologLOGIN);

        JOptionPane.showMessageDialog(null, "Clique ok após realizar login");

        System.out.println("Iniciando Sessão");
        WebDriverWait wait = (new WebDriverWait(browser, 60));
        String currentUrl = browser.getCurrentUrl();
        session = currentUrl.toString().substring(currentUrl.toString().indexOf("session=") + 8);

        browser.get(enderecoAcatamento + session);


// -------------------------------------------------------------------------------------------------------------------------------------------------
//      Contador baseado em tamanho do array
        int tamanhoArray = ORDEMSERVICO.size();
        System.out.println(tamanhoArray);
        for (int i = 0; i < tamanhoArray; i++) {


            System.out.println("identificar field AnoSolicitação");
            WebElement anoSolicitacao = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"P257_ANO_PEDIDO\"]")));
            System.out.println("inputando ano");
            anoSolicitacao.sendKeys(ANO.get(i));

            System.out.println("identificar field NºSolicitação");
            WebElement nmroSolicitacao = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"P257_NUM_PEDIDO\"]")));
            System.out.println("Vou preencher Nº da Solicitação");
            nmroSolicitacao.sendKeys(ORDEMSERVICO.get(i));
            Thread.sleep(200);

            System.out.println("Click botão pesquisar");
            WebElement pesquisar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("B295022948281420119")));
            pesquisar.click();
            Thread.sleep(1000);
            System.out.println("Clickado botão pesquisar");

            System.out.println("Click Editar");
            WebElement editar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[1]/div[2]/div[2]/main/div[2]/div/div[3]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[5]/div[1]/div/div[3]/table/tbody/tr[2]/td[1]/a")));
            editar.click();
            Thread.sleep(200);
            System.out.println("Clickado Editar");

            System.out.println("Click info execução");
            WebElement info = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[1]/div[2]/div[2]/main/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div[4]/div[3]/div[1]/div[2]/div[4]/table/tbody/tr/td[10]/a")));
            info.click();
            Thread.sleep(200);
            System.out.println("Clickado info execução");

            Integer lengthPadrao = 160;
            String caixaTexto = browser.findElement(By.name("P225_OBS")).getAttribute("value");
            String textoInterno = caixaTexto.substring(caixaTexto.indexOf("ETAPA - Executado:"));
            int textoInternoVariacao = textoInterno.length() - lengthPadrao;
            System.out.println(textoInterno);
            System.out.println(textoInterno.length());

            //substring -> (de onde, para onde) quero retornar cadeia de caracteres. No caso abaixo, usando indexOf pra dar o ponto de partida e ponto final da substring
            System.out.println("INDUSTRIAIS: " + textoInterno.substring(textoInterno.indexOf("QNTOS INDUSTRIAIS CONSTRUIDOS?: ") + 32, textoInterno.indexOf("QNTOS INDUSTRIAIS CONSTRUIDOS?: ") + 34).trim());
            System.out.println("PÚBLICOS: " + textoInterno.substring(textoInterno.indexOf("QNTOS PÚBLICOS CONSTRUÍDOS?: ") + 29, textoInterno.indexOf("QNTOS PÚBLICOS CONSTRUÍDOS?: ") + 31).trim());
            System.out.println("COMÉRCIOS: " + textoInterno.substring(textoInterno.indexOf("QNTOS COMÉRCIOS CONSTRUÍDOS?: ") + 30, textoInterno.indexOf("QNTOS COMÉRCIOS CONSTRUÍDOS?: ") + 32).trim());


// ----------------------------------------------------------------------------------------------------------------------
            if (textoInternoVariacao > 0) {
                System.out.println("RESIDÊNCIAS: " + textoInterno.substring(textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 31, textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + textoInternoVariacao).trim());
                valorRes = textoInterno.substring(textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 31, textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + textoInternoVariacao).trim();
            } else {
                System.out.println("RESIDÊNCIAS: " + textoInterno.substring(textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 31, textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 32).trim());
                valorRes = textoInterno.substring(textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 31, textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 32).trim();
            }

            valorInd = textoInterno.substring(textoInterno.indexOf("QNTOS INDUSTRIAIS CONSTRUIDOS?: ") + 32, textoInterno.indexOf("QNTOS INDUSTRIAIS CONSTRUIDOS?: ") + 34).trim();
            valorPub = textoInterno.substring(textoInterno.indexOf("QNTOS PÚBLICOS CONSTRUÍDOS?: ") + 29, textoInterno.indexOf("QNTOS PÚBLICOS CONSTRUÍDOS?: ") + 31).trim();
            valorCom = textoInterno.substring(textoInterno.indexOf("QNTOS COMÉRCIOS CONSTRUÍDOS?: ") + 30, textoInterno.indexOf("QNTOS COMÉRCIOS CONSTRUÍDOS?: ") + 32).trim();
//            String valorRes = textoInterno.substring(textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 31, textoInterno.indexOf("QNTS RESIDÊNCIAS CONSTRUÍDAS?: ") + 32).trim();

            System.out.println("Clickei em fechar");
            WebElement fechar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("B9187136310401088")));
            fechar.click();
            System.out.println("Clickado em fechar");
            Thread.sleep(500);


//-------------------------------------------------------------------------------------------------------------------------------------------------

//        TELA DE ALTERAÇÃO DA MATRÍCULA          /ords/prolagos/aegea/r/gss101/cad3010s1?session=";

            browser.get("https://gss.aegea.com.br/ords/prolagos/aegea/r/gss101/cad3010s1?session=" + session);

            System.out.println("Vai pegar ID do botão");
            Thread.sleep(500);
            WebElement lupa = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("R49514668791716612889_column_search_root")));
            System.out.println("Vou clicar na lupa");
            lupa.click();
            System.out.println("Cliquei na lupa");

            //continuar com seletor N
            WebElement opcaoNLigacao = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("R49514668791716612889_column_search_drop_2_c4i")));
            opcaoNLigacao.click();
            WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("R49514668791716612889_search_field")));
            searchField.sendKeys(MATRICULA.get(i));


            System.out.println("Vai pegar ID do botão ir");
            Thread.sleep(500);
            WebElement ir = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("R49514668791716612889_search_button")));
            System.out.println("Vou clicar em ir");
            ir.click();
            System.out.println("Cliquei em ir");


            System.out.println("Vou editar a matrícula");
            Thread.sleep(500);
            WebElement pincel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[1]/div[2]/div[2]/main/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[6]/div[1]/div/div[3]/table/tbody/tr[2]/td[1]/a/img")));
            System.out.println("Vou clicar no pincel");
            pincel.click();
            System.out.println("Cliquei no pincel");


            System.out.println("Vou editar o contrato");
            Thread.sleep(500);
            WebElement contrato = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("B66243573695058829")));
            System.out.println("Vou clicar em contrato");
            contrato.click();
            System.out.println("Cliquei em contrato");


            // MUDANÇA DE FRAME
            System.out.println("Vou mudar de frame");
            Thread.sleep(2000);
            WebElement frameContrato = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[2]/iframe")));
            browser.switchTo().frame(frameContrato);
            System.out.println("Mudei de frame");

            System.out.println("Vou editar segunda tela do contrato");

            System.out.println("Vou clicar na mão");
            Thread.sleep(600);
            WebElement mao = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div/div[2]/div/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[5]/div[1]/div/div[3]/table/tbody/tr[2]/td[1]/a")));
            mao.click();
            System.out.println("Cliquei na mão");

            Thread.sleep(600);
            System.out.println("Vou pegar id imóvel");
            WebElement imovel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div/div[2]/div/div/div/div[3]/div/div/div[1]/ul/li[2]/a")));
            Thread.sleep(600);
            System.out.println("Vou clicar no imóvel");
            imovel.click();
            System.out.println("Cliquei no imóvel");


            System.out.println("Se chegar aqui, deu certo");
//-----------------------------------------------------------------EDIÇÃO DO CÓDIGO DA ECONOMIA-------------------------------------------------------
//            Thread.sleep(10000);
//            if(valorComInt == 0 && valorIndInt == 0 && valorPubInt == 0){
//                WebElement campoCodigo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("P41_COD_UTILIZ")));
//                campoCodigo.sendKeys(valorRes);
//            }else{
//                System.out.println("é outra coisa :)");
//            }
//            Thread.sleep(10000);
            WebElement campoCodigo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("P41_COD_UTILIZ")));
            campoCodigo.clear();
            Thread.sleep(1000);
            campoCodigo.sendKeys("2");
            Thread.sleep(1000);
            campoCodigo.sendKeys(Keys.TAB);
//            WebElement campoCodigoBotao = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("P41_COD_UTILIZ_lov_btn")));
//            campoCodigoBotao.click();
            Thread.sleep(1000);


//
//            Thread.sleep(10000);
//            if(valorCom.equals(0)  && valorInd.equals(0) && valorPub.equals(0)){
//                campoCodigo.clear();
//                campoCodigo.sendKeys(valorRes);
//                campoCodigo.sendKeys(Keys.TAB);
//            }else if(valorInd.equals(1)){
//                System.out.println("1 industrial");
//                campoCodigo.clear();
//                campoCodigo.sendKeys(valorInd);
//                campoCodigo.sendKeys(Keys.TAB);
//            }
//            Thread.sleep(10000);

// -----------------------------------------------------------------EDIÇÃO DAS ECONOMIAS------------------------------------------------------
//            WebElement residencial = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("P41_QTD_ECONOMIAS_RES")));
//            residencial.clear();
//            Thread.sleep(400);
//            residencial.sendKeys(valorRes);

            WebElement comercial = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("P41_QTD_ECONOMIAS_COM")));
            comercial.clear();
            Thread.sleep(400);
            comercial.sendKeys("1");

//            WebElement industrial = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("P41_QTD_ECONOMIAS_IND")));
//            industrial.clear();
//            Thread.sleep(400);
//            industrial.sendKeys(valorInd);
//
//            WebElement publica = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("P41_QTD_ECONOMIAS_PUB")));
//            publica.clear();
//            Thread.sleep(400);
//            publica.sendKeys(valorPub);

            System.out.println("Vou clicar em salvar");
            Thread.sleep(1000);
            WebElement salvar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("B5675715685056943")));
            salvar.click();
            Thread.sleep(2000);
            WebElement clickBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
            System.out.println("Passei aqui");
            Thread.sleep(5000);
            clickBody.sendKeys(Keys.ENTER);
            Thread.sleep(1000);


//            WebElement divPossivel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[7]")));
//            browser.switchTo().frame(frameContrato2);
//            System.out.println("Mudei de frame");
//            Thread.sleep(1000);

//            System.out.println("Vou clicar em ok para confirmar alteração");
//            Thread.sleep(4000);
//            WebElement okSalvar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[7]")));
//            okSalvar.sendKeys(Keys.ENTER);
////            WebElement okSalvar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div[3]/div/button[2]")));
//            Thread.sleep(600);

//            okSave.click();

            Thread.sleep(600);
            WebElement voltar = wait.until(ExpectedConditions.elementToBeClickable(By.id("B66203060358058814")));
            voltar.click();
            Thread.sleep(600);

            browser.get(enderecoAcatamento + session);

        }
        JOptionPane.showMessageDialog(null, "Rotina realizada com sucesso!" + "\n" + "O EPP fica feliz em ajudar");

    }
}
