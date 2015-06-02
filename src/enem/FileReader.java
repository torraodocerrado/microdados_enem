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

    public void readFileCSVFields(String fileName, String separator, String nameTable) {
        File arquivoCSV = new File(fileName);
        System.out.println(arquivoCSV.getName());
        try {
            java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String[] atributos;
            String sql = "drop table if exists " + nameTable + "; create table " + nameTable + "(";
            while (linha != null) {
                if ((!linha.trim().equals(";;"))) {
                    atributos = linha.split(separator);
                    if ((atributos.length > 1) && (!atributos[0].isEmpty())) {
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

    public void readFileAndCreateTable(String fileName, String tableName, String fieldPK, String separator) {
        File arquivoCSV = new File(fileName);
        System.out.println(arquivoCSV.getName());
        try {
            java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha1 = lerArq.readLine();
            String linha2 = lerArq.readLine();
            String[] atributos1;
            String[] atributos2;
            String sql = "drop table if exists " + tableName + "; create table " + tableName + "(";
            if (!linha1.trim().equals(";;")) {
                atributos1 = linha1.split(separator);
                atributos2 = linha2.split(separator);
                for (int i = 0; i < atributos1.length; i++) {
                    atributos1[i] = atributos1[i].replace("\"", "");
                    atributos2[i] = atributos2[i].replace("\"", "");
                    sql += atributos1[i] + " ";
                    try {
                        Integer.parseInt(atributos2[i]);
                        sql += "int";
                    } catch (NumberFormatException e) {
                        if (atributos2[i].length() > 0) {
                            sql += "char(" + atributos2[i].length() + ")";
                        } else {
                            sql += "char(1)";
                        }
                    }
                    if (atributos1.length > i + 1) {
                        sql += ", \n";
                    }
                }
                linha1 = lerArq.readLine();
            }
            sql += "); ALTER TABLE public." + tableName + ""
                    + "  ALTER COLUMN " + fieldPK + " SET NOT NULL;\n"
                    + "\n"
                    + "ALTER TABLE public." + tableName + "  ADD PRIMARY KEY (" + fieldPK + ");";

            System.out.println();
            System.out.println("================================");
            System.out.println(sql);
            System.out.println();
            System.out.println("================================");
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public void insertQuestionarioEnem2012(String fileName) {
        String sql = "INSERT INTO public.questionario_enem_2012 ( nu_inscricao, in_qse, q01, q02, q03, q04, q05, q06, q07, q08, q09, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, q21, q22, q23, q24, q25, q26, q27, q28, q29, q30, q31, q32, q33, q34, q35, q36, q37, q38, q39, q40, q41, q42, q43, q44, q45, q46, q47, q48, q49, q50, q51, q52, q53, q54, q55, q56, q57, q58, q59, q60, q61, q62)\n"
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

    public void insertDadosEnem2012(String fileName) {
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

    public void insertItensEnem2012(String fileName) {
        String sql = "INSERT INTO itens_enem_2012(\n"
                + " id_posicao, cod_area, id_item, ds_gabarito, id_habilidade, ds_cor, "
                + " id_prova) VALUES (?, ?, ?, ?, ?, ?, ?);";
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
                            log.msgPrint("Inseridos: " + q);
                        }
                        int i = 1;
                        for (String atributo : atributos) {
                            atributo = atributo.replace("\"", "");
                            try {
                                int param = Integer.parseInt(atributo);
                                preparedStatement.setInt(i, param);
                            } catch (NumberFormatException | SQLException e) {
                                preparedStatement.setString(i, atributo.trim());
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

    public void insertDadosEnem2013(String fileName, String separator) {
        String sql = "INSERT INTO microdados_enem_2013(\n"
                + "            nu_inscricao, nu_ano, cod_municipio_residencia, no_municipio_residencia, \n"
                + "            cod_uf_residencia, uf_residencia, in_estuda_classe_hospitalar, \n"
                + "            cod_escola, cod_municipio_esc, no_municipio_esc, cod_uf_esc, \n"
                + "            uf_esc, id_dependencia_adm_esc, id_localizacao_esc, sit_func_esc, \n"
                + "            idade, tp_sexo, nacionalidade, cod_municipio_nascimento, no_municipio_nascimento, \n"
                + "            cod_uf_nascimento, uf_nascimento, st_conclusao, ano_concluiu, \n"
                + "            tp_escola, in_tp_ensino, tp_estado_civil, tp_cor_raca, in_baixa_visao, \n"
                + "            in_cegueira, in_surdez, in_deficiencia_auditiva, in_surdo_cegueira, \n"
                + "            in_deficiencia_fisica, in_deficiencia_mental, in_deficit_atencao, \n"
                + "            in_dislexia, in_gestante, in_lactante, in_idoso, in_autismo, \n"
                + "            in_sabatista, in_braille, in_ampliada_24, in_ampliada_18, in_ledor, \n"
                + "            in_acesso, in_transcricao, in_libras, in_leitura_labial, in_mesa_cadeira_rodas, \n"
                + "            in_mesa_cadeira_separada, in_apoio_perna, in_guia_interprete, \n"
                + "            in_certificado, no_entidade_certificacao, cod_uf_entidade_certificacao, \n"
                + "            uf_entidade_certificacao, cod_municipio_prova, no_municipio_prova, \n"
                + "            cod_uf_prova, uf_prova, in_presenca_cn, in_presenca_ch, in_presenca_lc, \n"
                + "            in_presenca_mt, id_prova_cn, id_prova_ch, id_prova_lc, id_prova_mt, \n"
                + "            nota_cn, nota_ch, nota_lc, nota_mt, tx_respostas_cn, tx_respostas_ch, \n"
                + "            tx_respostas_lc, tx_respostas_mt, tp_lingua, gabarito_cn, gabarito_ch, \n"
                + "            gabarito_lc, gabarito_mt, in_status_redacao, nu_nota_comp1, nu_nota_comp2, \n"
                + "            nu_nota_comp3, nu_nota_comp4, nu_nota_comp5, nu_nota_redacao, \n"
                + "            q001, q002, q003, q004, q005, q006, q007, q008, q009, q010, q011, \n"
                + "            q012, q013, q014, q015, q016, q017, q018, q019, q020, q021, q022, \n"
                + "            q023, q024, q025, q026, q027, q028, q029, q030, q031, q032, q033, \n"
                + "            q034, q035, q036, q037, q038, q039, q040, q041, q042, q043, q044, \n"
                + "            q045, q046, q047, q048, q049, q050, q051, q052, q053, q054, q055, \n"
                + "            q056, q057, q058, q059, q060, q061, q062, q063, q064, q065, q066, \n"
                + "            q067, q068, q069, q070, q071, q072, q073, q074, q075, q076)\n"
                + "    VALUES (?, ?, ?, ?, \n"
                + "            ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, \n"
                + "            ?, ?, ?, \n"
                + "            ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            Driver driver = new Driver();
            PreparedStatement preparedStatement;
            File arquivoCSV = new File(fileName);
            System.out.println(arquivoCSV.getName());
            try {
                java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
                BufferedReader lerArq = new BufferedReader(arq);
                String cabecalho = lerArq.readLine();
                String linha = lerArq.readLine();
                System.out.println(linha);
                String[] cabecalhos = cabecalho.split(separator);
                String[] atributos;
                int q = 1;
                String atributo;
                while (linha != null) {
                    if (!linha.trim().equals(";;")) {
                        preparedStatement = driver.prepareStatement(sql);
                        atributos = linha.split(separator);
                        if (q++ % 5000 == 0) {
                            log.msgPrint("Inseridos: " + q);
                        }
                        for (int i = 1; i < 167; i++) {
                            if (atributos.length < i) {
                                atributo = "";
                            } else {
                                atributo = atributos[i - 1];
                                atributo = atributo.replace("\"", "");
                            }
//                            System.out.println(i + cabecalhos[i - 1]);
//                            System.out.println(atributo);

                            switch (i) {
                                case 1:
                                    preparedStatement.setString(i, atributo);
                                    break;
                                case 8:
                                case 9:
                                case 11:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 19:
                                case 24:
                                case 25:
                                case 26:
                                case 85:
                                case 86:
                                case 87:
                                case 88:
                                case 89:
                                case 130:
                                    if (atributo.trim().equals(".") || atributo.isEmpty()) {
                                        preparedStatement.setNull(i, java.sql.Types.INTEGER);
                                    } else {
                                        preparedStatement.setInt(i, Integer.parseInt(atributo));
                                    }
                                    break;
                                case 71:
                                case 72:
                                case 73:
                                case 74:
                                    if (atributo.trim().equals(".") || atributo.isEmpty()) {
                                        preparedStatement.setNull(i, java.sql.Types.INTEGER);
                                    } else {
                                        preparedStatement.setDouble(i, Double.parseDouble(atributo));
                                    }
                                    break;
                                default:
                                    try {
                                        if (atributo.isEmpty()) {
                                            preparedStatement.setNull(i, java.sql.Types.CHAR);
                                        } else {
                                            preparedStatement.setInt(i, Integer.parseInt(atributo));
                                        }
                                    } catch (NumberFormatException e) {
                                        preparedStatement.setString(i, atributo.trim());
                                    }
                            }
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

    void insertConsistenciaEnemEscola2013(String fileName, String separator) {
        String sql = "INSERT INTO consistencia_enem_escola_2013(\n"
                + "            id_enem_escola, nu_inscricao, nu_ano, cod_escola, cod_uf_esc, \n"
                + "            id_dependencia_adm_esc, id_localizacao_esc)\n"
                + "    VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            Driver driver = new Driver();
            PreparedStatement preparedStatement;
            File arquivoCSV = new File(fileName);
            System.out.println(arquivoCSV.getName());
            try {
                java.io.FileReader arq = new java.io.FileReader(arquivoCSV);
                BufferedReader lerArq = new BufferedReader(arq);
                String cabecalho = lerArq.readLine();
                String linha = lerArq.readLine();
                System.out.println(linha);
                String[] cabecalhos = cabecalho.split(separator);
                String[] atributos;
                int q = 1;
                String atributo;
                while (linha != null) {
                    if (!linha.trim().equals(";;")) {
                        preparedStatement = driver.prepareStatement(sql);
                        atributos = linha.split(separator);
                        if (q++ % 5000 == 0) {
                            log.msgPrint("Inseridos: " + q);
                        }
                        for (int i = 1; i < 8; i++) {
                            if (atributos.length < i) {
                                atributo = "";
                            } else {
                                atributo = atributos[i - 1];
                                atributo = atributo.replace("\"", "");
                            }

                            switch (i) {
                                case 2:
                                    preparedStatement.setString(i, atributo);
                                    break;
                                default:
                                    if (atributo.trim().equals(".") || atributo.isEmpty()) {
                                        preparedStatement.setNull(i, java.sql.Types.INTEGER);
                                    } else {
                                        preparedStatement.setInt(i, Integer.parseInt(atributo));
                                    }
                            }
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
