/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enem;

/**
 *
 * @author Rafael
 */
public class Enem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader file = new FileReader();
//        file.readFileCSVFields("data/microdados_enem2013/dicionario2.csv", ";", "microdados_enem_2013");
//        file.readFileSample("data/microdados_enem2013/DADOS/MICRODADOS_ENEM_2013.csv");
        file.insertConsistenciaEnemEscola2013("data/microdados_enem2013/DADOS/CONSISTENCIA_ENEM_ESCOLA_2013.csv", ",");
    }

    public void importData(FileReader file) {
        file.insertItensEnem2012("data/microdados_enem2012/DADOS/ITENS_ENEM_2012.csv");
        file.insertDadosEnem2012("data/microdados_enem2012/DADOS/DADOS_ENEM_2012.csv");
        file.insertQuestionarioEnem2012("data/microdados_enem2012/DADOS/QUESTIONARIO_ENEM_2012.csv");
        file.insertDadosEnem2013("data/microdados_enem2013/DADOS/MICRODADOS_ENEM_2013.csv", ";");
    }

}
