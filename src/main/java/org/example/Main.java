package org.example;

public class Main {
    final static String url = "https://www.ote-cr.cz/pubweb/attachments/05_09_12/2022/month12/day21/Imbalances_21_12_2022_V0_EN.xls";

    public static void main(String[] args) {
        String localFilePath = new CustomFileDownloader().downloadXlsFile(url);
        new CustomXmlParser().parseFileAndPrintOutput(localFilePath);
    }
}
