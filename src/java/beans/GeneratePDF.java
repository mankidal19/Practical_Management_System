/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import db_conn.ConnectionUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import utils.CoordinatorFunctionUtils;
import utils.MyUtils;

/**
 *
 * @author Yong Keong
 */
public class GeneratePDF {
    private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
     private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

    /**
     * @param args
     */
    public static Document createPDF(String file, Coordinator coordinator) throws SQLException, ClassNotFoundException {
            Rectangle pageSize=new Rectangle(1366f,1280f); 

            Document document = null;

            try {
                    document = new Document(pageSize);
                    PdfWriter.getInstance(document, new FileOutputStream(file));
                    document.open();

                    addMetaData(document, coordinator.getCoordinatorName());

                    addTitlePage(document, coordinator.getCoordinatorName());

                    createTable(document, coordinator.getCoordinatorId());

                    document.close();

            } catch (FileNotFoundException | DocumentException e) {

                    e.printStackTrace();
            }
            return document;

    }

    private static void addMetaData(Document document, String name) {
            document.addTitle("Student report");
            document.addSubject("Student report");
            document.addAuthor(name);
            document.addCreator(name);
    }

    private static void addTitlePage(Document document, String name)
                    throws DocumentException {

            Paragraph preface = new Paragraph();
            creteEmptyLine(preface, 1);
            preface.add(new Paragraph("Student Report", TIME_ROMAN));

            creteEmptyLine(preface, 1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            preface.add(new Paragraph("Report created on "
                            + simpleDateFormat.format(new Date()) + " by " + name, TIME_ROMAN_SMALL));
            document.add(preface);

    }

    private static void creteEmptyLine(Paragraph paragraph, int number) {
            for (int i = 0; i < number; i++) {
                    paragraph.add(new Paragraph(" "));
            }
    }

    private static void createTable(Document document, String coID) throws DocumentException, SQLException, ClassNotFoundException {
            Paragraph paragraph = new Paragraph();
            creteEmptyLine(paragraph, 2);
            document.add(paragraph);
            PdfPTable table = new PdfPTable(8);

            PdfPCell c1 = new PdfPCell(new Phrase("Student Matric No", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Student Name", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Student Gender", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Student Contact", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Student Email", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Student Course", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Student CGPA", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Student Internship Status", boldFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            table.setHeaderRows(1);
            
            Connection conn = ConnectionUtils.getConnection();
            List<Student> list = null;
            list = CoordinatorFunctionUtils.queryStudent(conn,coID);
            for (int i = 0; i < list.size(); i++) {
                    table.setWidthPercentage(100);
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(list.get(i).getStd_matric());
                    table.addCell(list.get(i).getStd_name());
                    if("M".equals(list.get(i).getStd_gender())){
                        table.addCell("Male");
                    }else if ("F".equals(list.get(i).getStd_gender())){
                        table.addCell("Female");
                    }
                    table.addCell(list.get(i).getStd_contact());
                    table.addCell(list.get(i).getStd_email());
                    table.addCell(list.get(i).getStd_course());
                    table.addCell(String.valueOf(list.get(i).getStd_cgpa()));
                    if("A".equals(list.get(i).getStd_status())){
                        table.addCell("Accepted");
                    }else if ("P".equals(list.get(i).getStd_status())){
                        table.addCell("Pending");
                    }else if ("R".equals(list.get(i).getStd_status())){
                        table.addCell("Rejected");
                    }
                    
                    
            }

            document.add(table);
    }
}
