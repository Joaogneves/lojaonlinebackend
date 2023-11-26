package com.example.lojaonline.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

import com.example.lojaonline.entity.car.Car;
import com.example.lojaonline.entity.cliente.Cliente;
import com.example.lojaonline.entity.cliente.address.ClienteAddress;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

public class Pdf {

	public void create(ClienteAddress ca) throws DocumentException, FileNotFoundException {
		Document doc = new Document(PageSize.A4);
		Font fontBold = new Font(Font.HELVETICA, 22, Font.BOLDITALIC);
		Font fontNormal = new Font(Font.HELVETICA, 18, Font.NORMAL);
		PdfWriter.getInstance(doc, new FileOutputStream("src\\downloads\\Ficha-" + ca.getId() + ".pdf"));
		doc.open();
		Paragraph header = new Paragraph();
		header.add(new Chunk("FICHA DE CADASTRO NA LOJA", fontBold));
		header.setAlignment(Element.ALIGN_CENTER);
		header.setSpacingAfter(10);
		doc.add(header);
		doc.add(new Paragraph(" "));
		Paragraph data = new Paragraph();
		data.add(new Chunk("DADOS PESSOAIS", fontBold));
		data.setSpacingAfter(10);
		data.setAlignment(Element.ALIGN_CENTER);
		doc.add(data);
		doc.add(new Paragraph(" "));
		Paragraph name = new Paragraph();
		name.add(new Chunk("Nome: " + ca.getCliente().getFullName(), fontNormal));
		name.setSpacingAfter(10);
		doc.add(name);
		String pattern = "###.###.###-##";
		String fstring = ca.getCliente().getCpf();
		Paragraph cpf = new Paragraph();
		cpf.add(new Chunk("CPF: " + format(pattern, fstring), fontNormal));
		cpf.setSpacingAfter(10);
		doc.add(cpf);
		pattern = "##.###.###-#";
		fstring = ca.getCliente().getRg();
		Paragraph rg = new Paragraph();
		rg.add(new Chunk("RG: " + format(pattern, fstring), fontNormal));
		rg.setSpacingAfter(10);
		doc.add(rg);
		Paragraph cnh = new Paragraph();
		cnh.add(new Chunk("CNH: " + ca.getCliente().getCnh(), fontNormal)); 
		cnh.setSpacingAfter(10);
		doc.add(cnh);
		Paragraph motherName = new Paragraph();
		motherName.add(new Chunk("Nome da Mãe: " + ca.getCliente().getMotherName(), fontNormal));
		motherName.setSpacingAfter(10);
		doc.add(motherName);
		Paragraph fatherName = new Paragraph();
		fatherName.add(new Chunk("Nome do Pai: " + ca.getCliente().getFatherName(), fontNormal));
		fatherName.setSpacingAfter(10);
		doc.add(fatherName);
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date bd = ca.getCliente().getBirthdate();
		Paragraph birth = new Paragraph();
		birth.add(new Chunk("Data de Nascimento: " + fmt.format(bd), fontNormal));
		birth.setSpacingAfter(10);
		doc.add(birth);
		Paragraph ws = new Paragraph();
		ws.add(new Chunk("Celular/Whatsapp: " + ca.getCliente().getWhatsapp(), fontNormal));
		ws.setSpacingAfter(10);
		doc.add(ws);
		Paragraph email = new Paragraph();
		email.add(new Chunk("Email: " + ca.getCliente().getEmail(), fontNormal));
		email.setSpacingAfter(10);
		doc.add(email);
		Paragraph nat = new Paragraph();
		nat.add(new Chunk("Naturalidade: " + ca.getCliente().getCitizenship(), fontNormal));
		nat.setSpacingAfter(10);
		doc.add(nat);
		Paragraph ms = new Paragraph();
		ms.add(new Chunk("Estado civil: " + ca.getCliente().getMaritalStatus(), fontNormal));
		ms.setSpacingAfter(10);
		doc.add(ms);
		Paragraph gender = new Paragraph();
		gender.add(new Chunk("Sexo: " + ca.getCliente().getGender(), fontNormal));
		gender.setSpacingAfter(10);
		doc.add(gender);
		doc.add(new Paragraph(" "));
		Paragraph address = new Paragraph();
		address.add(new Chunk("ENDEREÇO", fontBold)); 
		address.setAlignment(Element.ALIGN_CENTER);
		address.setSpacingAfter(10);
		doc.add(address);
		doc.add(new Paragraph(" "));
		pattern = "#####-###";
		fstring = ca.getAddress().getCep();
		Paragraph cep = new Paragraph(); 
		cep.add(new Chunk("CEP: " + format(pattern, fstring), fontNormal));
		cep.setSpacingAfter(10);
		doc.add(cep);
		Paragraph street = new Paragraph();
		street.add(new Chunk("Rua: " + ca.getAddress().getStreetName() + ",nº " + ca.getAddress().getStreetNumber(), fontNormal));
		street.setSpacingAfter(10);
		doc.add(street);
		String cp = ca.getAddress().getComplement() != null ? ca.getAddress().getComplement() : "";
		Paragraph comp = new Paragraph();
		comp.add(new Chunk("Complemento: " + cp, fontNormal));
		comp.setSpacingAfter(10);
		doc.add(comp);
		Paragraph br = new Paragraph();
		br.add(new Chunk("Bairro: " + ca.getAddress().getNeighborhood(), fontNormal));
		br.setSpacingAfter(10);
		doc.add(br);
		Paragraph city = new Paragraph();
		city.add(new Chunk("Cidade: " + ca.getAddress().getCity(), fontNormal));
		city.setSpacingAfter(10);
		doc.add(city);
		Paragraph uf = new Paragraph();
		uf.add(new Chunk("UF: " + ca.getAddress().getUf(), fontNormal));
		uf.setSpacingAfter(10);
		doc.add(uf);
		doc.close();
	}

	private static String format(String pattern, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public void createContract(Car car, Cliente c) throws DocumentException, FileNotFoundException {
		File file = new File("src\\main\\resources\\contract.txt");
		Scanner sc = null;
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, new FileOutputStream("src\\downloads\\contract-" + c.getId() +".pdf"));
		doc.open();
		try {
			sc = new Scanner(file, StandardCharsets.UTF_8);
			while (sc.hasNextLine()) {
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				String line = sc.nextLine();
				line = line.replace("$CLIENTE_NOME", c.getFullName());
				line = line.replace("$CLIENTE_CPF", c.getCpf());
				DecimalFormat f = new DecimalFormat("#.##");
				String nf = f.format(car.getPrice());
				line = line.replace("$CAR_PRICE", nf + ",00");
				line = line.replace("$CAR_MODELO", car.getCarBrand() + " " + car.getName() + " " + car.getCarYear());
				String pattern = "UUU-AAAA";
				String fstring = car.getPlaca();
				line = line.replace("$CAR_PLACA", format(pattern, fstring));
				line = line.replace("$CAR_RENAVAN", car.getRenavan());
				Date td = new Date();
				line = line.replace("$DATE", fmt.format(td));
				byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
				String textF = new String(bytes, StandardCharsets.UTF_8);
				Paragraph p = new Paragraph();
				p.add(new Phrase(textF));
				p.setSpacingAfter(10);
				doc.add(p);

			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if(sc != null) {
				doc.close();
				sc.close();
			}
		}
	}
}
