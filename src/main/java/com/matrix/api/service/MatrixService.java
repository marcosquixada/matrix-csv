package com.matrix.api.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatrixService {

    //Return the matrix as a string in matrix format.
    public String echo(MultipartFile file){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            String result = "";
            for (CSVRecord csvRecord : csvRecords) {
                result += csvRecord.toString().split("values=")[1].replace("[", "").replace("]", "") + System.lineSeparator();
            }

            return result;
        } catch (IOException e) {
            return "Fail to parse CSV file: " + e.getMessage();
        } catch (Exception e) {
            return "Fail processing CSV file: " + e.getMessage();
        }
    }

    //Return the matrix as a string in matrix format where the columns and rows are inverted.
    public String invert(MultipartFile file){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            String result = "";
            List<List<String>> matrix = new ArrayList<>();
            for (CSVRecord csvRecord : csvRecords) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < csvRecord.size(); i++) {
                    row.add(csvRecord.get(i));
                }
                matrix.add(row);
            }

            for(int i=0;i<matrix.size();i++) {
                for (int j = 0; j < matrix.get(i).size(); j++) {
                    result += matrix.get(j).get(i) + ", ";
                }
                result = result.substring(0, result.length() - 2);
                result += System.lineSeparator();
            }

            return result;
        } catch (IOException e) {
            return "Fail to parse CSV file: " + e.getMessage();
        } catch (Exception e) {
            return "Fail processing CSV file: " + e.getMessage();
        }
    }

    //Return the matrix as a 1 line string, with values separated by commas.
    public String flatten(MultipartFile file){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            String result = "";
            for (CSVRecord csvRecord : csvRecords) {
                result += csvRecord.toString().split("values=")[1].replace("[", "").replace("]", "") + ", ";
            }

            return result.substring(0, result.length() - 2);
        } catch (IOException e) {
            return "Fail to parse CSV file: " + e.getMessage();
        } catch (Exception e) {
            return "Fail processing CSV file: " + e.getMessage();
        }
    }

    //Return the sum of the integers in the matrix.
    public Long sum(MultipartFile file){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            BigInteger result = new BigInteger("0");
            for (CSVRecord csvRecord : csvRecords) {
                for (int i = 0; i < csvRecord.size(); i++) {
                    result = result.add(new BigInteger(csvRecord.get(i)));
                }
            }
            return result.longValue();
        } catch (Exception e) {
            return -1L;
        }
    }

    //Return the multiple of the integers in the matrix
    public Long multiply(MultipartFile file){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            BigInteger result = new BigInteger("1");
            for (CSVRecord csvRecord : csvRecords) {
                for (int i = 0; i < csvRecord.size(); i++) {
                    result = result.multiply(new BigInteger(csvRecord.get(i)));
                }
            }
            return result.longValue();
        } catch (Exception e) {
            return -1L;
        }
    }

    private static void csvReader(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                System.out.println(csvRecord);
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //System.out.println("CSVRecord [comment='null', recordNumber=3, values=[7, 8, 9]]".split("values=")[1].replace("[", "").replace("]", ""));
    }
}
