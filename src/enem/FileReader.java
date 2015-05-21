/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enem;

import bib.base.Base;
import bib.driver.Driver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Rafael
 */
public class FileReader extends Base {

    public void readFileCSVFields(String fileName) {
        File arquivoCSV = new File(fileName);
        System.out.println(arquivoCSV.getName());
        try {
            java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String[] atributos;
            String sql = "drop table if exists arquivo; create table arquivo(";
            while (linha != null) {
                if (!linha.trim().equals(";;")) {
                    atributos = linha.split(";");

                    if ((atributos.length > 0) && (!atributos[0].isEmpty())) {
                        sql += atributos[0] + " ";
                        switch (atributos[2]) {
                            case "Alfanumerica":
                                sql += "char(" + atributos[1] + "), \n";
                                break;
                            case "Numerica":
                                sql += "int, \n";
                                break;
                        }
                    }
                }
                linha = lerArq.readLine();
            }
            sql += ");";
            System.out.println(sql);
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public void readFileSample(String fileName) {
        File arquivoCSV = new File(fileName);
        System.out.println(arquivoCSV.getName());
        try {
            java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            System.out.println(linha);
            linha = lerArq.readLine();
            System.out.println(linha);
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public void readFileQuestionario(String fileName) {
        File arquivoCSV = new File(fileName);
        System.out.println(arquivoCSV.getName());
        try {
            java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha1 = lerArq.readLine();
            String linha2 = lerArq.readLine();
            String[] atributos1;
            String[] atributos2;
            String sql = "drop table if exists questionario; create table questionario(";
            while (linha1 != null) {
                if (!linha1.trim().equals(";;")) {
                    atributos1 = linha1.split(";");
                    atributos2 = linha2.split(";");
                    for (int i = 0; i < atributos1.length; i++) {
                        atributos1[i] = atributos1[i].replace("\"", "");
                        atributos2[i] = atributos2[i].replace("\"", "");
                        sql += atributos1[i] + " ";
                        try {
                            Integer.parseInt(atributos2[i]);
                            sql += "int, \n";
                        } catch (NumberFormatException e) {
                            if (atributos2[i].length() > 0) {
                                sql += "char(" + atributos2[i].length() + "), \n";
                            } else {
                                sql += "char(1), \n";
                            }
                        }

                    }
                }
                linha1 = lerArq.readLine();
            }
            sql += "); ALTER TABLE public.questionario\n"
                    + "  ALTER COLUMN nu_inscricao SET NOT NULL;\n"
                    + "\n"
                    + "ALTER TABLE public.questionario\n"
                    + "  ADD PRIMARY KEY (nu_inscricao);";
            System.out.println(sql);
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public void insertCSVIntoDatabase(String fileName) {
        String sql = "INSERT INTO public.questionario ( nu_inscricao, in_qse, q01, q02, q03, q04, q05, q06, q07, q08, q09, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, q21, q22, q23, q24, q25, q26, q27, q28, q29, q30, q31, q32, q33, q34, q35, q36, q37, q38, q39, q40, q41, q42, q43, q44, q45, q46, q47, q48, q49, q50, q51, q52, q53, q54, q55, q56, q57, q58, q59, q60, q61, q62)\n"
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            Driver driver = new Driver();
            PreparedStatement preparedStatement;
            File arquivoCSV = new File(fileName);
            System.out.println(arquivoCSV.getName());
            try {
                java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
                BufferedReader lerArq = new BufferedReader(arq);
                String linha = lerArq.readLine();
                linha = lerArq.readLine();
                String[] atributos;
                int q = 1;
                while (linha != null) {
                    if (!linha.trim().equals(";;")) {
                        preparedStatement = driver.prepareStatement(sql);
                        atributos = linha.split(",");
                        if (q++ % 5000 == 0) {
                            log.msgPrint("Inseridos: " + q);
                        }
                        int i = 1;
                        for (String atributo : atributos) {
                            atributo = atributo.replace("\"", "");
                            switch (i) {
                                case 1:
                                    preparedStatement.setString(i, atributo);
                                    break;
                                default:
                                    try {
                                        int param = Integer.parseInt(atributo);
                                        preparedStatement.setInt(i, param);
                                    } catch (NumberFormatException | SQLException e) {
                                        preparedStatement.setString(i, atributo.trim());
                                    }
                            }
                            i++;;
                        }
                        driver.executeUpdate(preparedStatement);
                    }
                    linha = lerArq.readLine();
                }
                arq.close();
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Erro geral: " + ex.getMessage());
        }
    }

    public void countLineFile(String fileName) {
        try {
            File arquivoCSV = new File(fileName);
            System.out.println(arquivoCSV.getName());
            java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            int i = 0;
            while (linha != null) {
                linha = lerArq.readLine();
                i++;
            }
            System.out.println("Tamanho do arquivo: " + i + " linhas.");
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public void insertCSVDataIntoDatabase(String fileName) {

        String sql = "INSERT INTO public.dados_enem_2012( nu_inscricao, nu_ano, idade, tp_sexo, cod_municipio_insc, no_municipio_insc, uf_insc, st_conclusao, ano_concluiu, tp_escola, in_tp_ensino, tp_estado_civil, tp_cor_raca, in_unidade_hospitalar, in_baixa_visao, in_cegueira, in_surdez, in_deficiencia_auditiva, in_surdo_cegueira, in_deficiencia_fisica, in_deficiencia_mental, in_deficit_atencao, in_dislexia, in_gestante, in_lactante, in_idoso, in_autismo, in_sabatista, in_braille, in_ampliada, in_ledor, in_acesso, in_transcricao, in_libras, in_leitura_labial, in_mesa_cadeira_rodas, in_mesa_cadeira_separada, in_apoio_perna, in_guia_interprete, in_certificado, no_entidade_certificacao, uf_entidade_certificacao, pk_cod_entidade, cod_municipio_esc, no_municipio_esc, uf_esc, id_dependencia_adm, id_localizacao, sit_func, cod_municipio_prova, no_municipio_prova, uf_municipio_prova, in_presenca_cn, in_presenca_ch, in_presenca_lc, in_presenca_mt, nu_nt_cn, nu_nt_ch, nu_nt_lc, nu_nt_mt, tx_respostas_cn, tx_respostas_ch, tx_respostas_lc, tx_respostas_mt, id_prova_cn, id_prova_ch, id_prova_lc, id_prova_mt, tp_lingua, ds_gabarito_cn, ds_gabarito_ch, ds_gabarito_lc, ds_gabarito_mt, in_status_redacao, nu_nota_comp1, nu_nota_comp2, nu_nota_comp3, nu_nota_comp4, nu_nota_comp5, nu_nota_redacao) \n"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            Driver driver = new Driver();
            PreparedStatement preparedStatement;
            File arquivoCSV = new File(fileName);
            System.out.println(arquivoCSV.getName());
            try {
                java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
                BufferedReader lerArq = new BufferedReader(arq);
                String linha = lerArq.readLine();
                System.out.println(linha);
                linha = lerArq.readLine();
                String[] atributos;
                int q = 1;
                while (linha != null) {
                    if (!linha.trim().equals(";;")) {
                        preparedStatement = driver.prepareStatement(sql);
                        atributos = linha.split(",");
                        if (q++ % 5000 == 0) {
                            log.msgPrint("Inseridos DADOS 2012: " + q);
                        }
                        int i = 1;
                        for (String atributo : atributos) {
                            atributo = atributo.replace("\"", "");
                            switch (i) {
                                case 1:
                                    preparedStatement.setString(i, atributo);
                                    break;
                                case 9:
                                case 10:
                                case 11:
                                case 43:
                                case 44:
                                case 47:
                                case 48:
                                case 49:
                                case 65:
                                case 66:
                                case 67:
                                case 68:
                                case 69:
                                case 75:
                                case 76:
                                case 77:
                                case 78:
                                case 79:
                                case 80:
                                    if (atributo.trim().equals(".") || atributo.isEmpty()) {
                                        preparedStatement.setNull(i, java.sql.Types.INTEGER);
                                    } else {
                                        preparedStatement.setInt(i, Integer.parseInt(atributo));
                                    }
                                    break;
                                case 57:
                                case 58:
                                case 59:
                                case 60:
                                    if (atributo.trim().equals(".") || atributo.isEmpty()) {
                                        preparedStatement.setNull(i, java.sql.Types.INTEGER);
                                    } else {
                                        preparedStatement.setDouble(i, Double.parseDouble(atributo));
                                    }
                                    break;
                                default:
                                    try {
                                        preparedStatement.setInt(i, Integer.parseInt(atributo));
                                    } catch (NumberFormatException e) {
                                        preparedStatement.setString(i, atributo.trim());
                                    }
                            }
                            i++;;
                        }
                        driver.executeUpdate(preparedStatement);
                    }
                    linha = lerArq.readLine();
                }
                arq.close();
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Erro geral: " + ex.getMessage());
        }

    }
}
