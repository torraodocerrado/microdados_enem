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
//        file.readFileCSVFields("data/fields.csv");
//        file.readFileQuestionario("data/exemplo.csv");
//        file.insertCSVDataIntoDatabase("data/DADOS_ENEM_2012.csv");
//        file.readFileSample("data/DADOS_ENEM_2012.csv");
        file.countLineFile("data/QUESTIONARIO_ENEM_2012.csv");
    }

}
