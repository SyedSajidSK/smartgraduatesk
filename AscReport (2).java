package mncproject;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AscReport {

	public static void main(String[] args)throws Exception {
		
		Document document=new Document();
		
		PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("C:\\Generate_PDF\\AscendingOrderR_PDF2.pdf"));
		
		document.open();
		
		
		PdfPTable table=new PdfPTable(8);
		
		PdfPCell cell;
		
		
		cell=new PdfPCell(new Phrase("Student_id"));
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("course_id"));
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("cod"));
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("qod"));
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("tod"));
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("low"));
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("vow"));
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("Total"));
		table.addCell(cell);
		
		
		Connection con=ConnectionUtility.getConnection();
		panchatanthra_DAO pd=new Panchatanthra_DAOImpl();
		Collection<PanchatanthraDTO> pt=pd.findAll(con);
		Iterator<PanchatanthraDTO>iter=pt.iterator();
		while(iter.hasNext()) {
			
			
			PanchatanthraDTO pt1=iter.next();
			int sid=pt1.getStudent_id();
			
			System.out.println(sid);
			
			
			int cid=pt1.getCourse_id();
			
			
			int cod=pt1.getCod_count();
			
			
			int qod=pt1.getQod_count();
			
			
			int tod=pt1.getTod_count();
			
			
			int low=pt1.getLow_count();
			
			
			
			int vow=pt1.getVow_count();
			
		
			
			
			int total=pt1.getCod_count()*5+pt1.getQod_count()*5+pt1.getTod_count()*5+pt1.getLow_count()*25+pt1.getVow_count()*25;
			float percentage=(total*100)/125;
			
			
			
			Integer tot=cod+qod+tod+low+vow;
			pt1.setTotal(tot);
			
		}
			Collections.sort((List<PanchatanthraDTO>)pt,(o1,o2)->{
				return o1.getTotal().compareTo(o2.getTotal())
						;
			});
			
			Iterator<PanchatanthraDTO>iter2=pt.iterator();
			while(iter2.hasNext()) {
				
				
				PanchatanthraDTO pt1=iter2.next();
				int sid=pt1.getStudent_id();
				
				System.out.println(sid);
				cell=new PdfPCell(new Phrase(String.valueOf(sid)));
				table.addCell(cell);
				
				int cid=pt1.getCourse_id();
				cell=new PdfPCell(new Phrase(String.valueOf(cid)));
				table.addCell(cell);
				
				int cod=pt1.getCod_count();
				cell=new PdfPCell(new Phrase(String.valueOf(cod)));
				table.addCell(cell);
				
				int qod=pt1.getQod_count();
				cell=new PdfPCell(new Phrase(String.valueOf(qod)));
				table.addCell(cell);
				
				int tod=pt1.getTod_count();
				cell=new PdfPCell(new Phrase(String.valueOf(tod)));
				table.addCell(cell);
				
				int low=pt1.getLow_count();
				cell=new PdfPCell(new Phrase(String.valueOf(low)));
				table.addCell(cell);
				
				
				int vow=pt1.getVow_count();
				cell=new PdfPCell(new Phrase(String.valueOf(vow)));
				table.addCell(cell);
				
				
				int total=pt1.getCod_count()*5+pt1.getQod_count()*5+pt1.getTod_count()*5+pt1.getLow_count()*25+pt1.getVow_count()*25;
				float percentage=(total*100)/125;
				cell=new PdfPCell(new Phrase(String.valueOf(percentage)));
				table.addCell(cell);
				
				Integer tot=cod+qod+tod+low+vow;
				pt1.setTotal(tot);
				
			}
			
		
		
		
		document.add(table);
		
		document.close();
		
		System.out.println("PDF Created");
	}


}

	
