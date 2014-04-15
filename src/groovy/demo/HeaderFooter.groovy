package demo

import com.lowagie.text.Document
import com.lowagie.text.DocumentException
import com.lowagie.text.Element
import com.lowagie.text.Image
import com.lowagie.text.Paragraph
import com.lowagie.text.Phrase
import com.lowagie.text.Rectangle
import com.lowagie.text.pdf.Barcode128
import com.lowagie.text.pdf.PdfContentByte
import com.lowagie.text.pdf.PdfPageEventHelper
import com.lowagie.text.pdf.PdfWriter

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.image.BufferedImage

/** Inner class to add a header and a footer. */
class HeaderFooter extends PdfPageEventHelper {

    String barCodeImageData

    Phrase[] header = new Phrase[2];

    int pagenumber;

    /**
     * Initialize one of the headers.
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        header[0] = new Phrase("Movie history 0");
        header[1] = new Phrase("Movie history 1");
    }

    /**
     * Increase the page number.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onStartPage(PdfWriter writer, Document document) {
        pagenumber++;
    }

    /**
     * Adds the header and the footer.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle rect = writer.getBoxSize("art");

        System.out.println(writer.getPageNumber() + " - " + barCodeImageData);

        Image element;
        Image img1;

        Image img2;


        try {
            img1 = Image.getInstance("C:/BarCodeQRCodeGenerator/barcode.png");

            String RESULT1 = "c:/BarCodeQRCodeGenerator/barcodes2.pdf";

            // step 1
            Document document1 = new Document(new Rectangle(340, 842));
            // step 2
            PdfWriter writer1 = PdfWriter.getInstance(document1, new FileOutputStream(RESULT1));
            // step 3
            document1.open();
            // step 4
            PdfContentByte cb = writer1.getDirectContent();

            // CODE 128
            document1.add(new Paragraph("Barcode 128"));
            Barcode128 code128 = new Barcode128();



            String plainText = "Password";
            String encryptedText = AESEncryption.encrypt(plainText);
            String decryptedText = AESEncryption.decrypt(encryptedText);

            System.out.println("Plain Text : " + plainText);
            System.out.println("Encrypted Text : " + encryptedText);
            System.out.println("Decrypted Text : " + decryptedText);

            String encryptedBarCodeImageData = AESEncryption.encrypt(barCodeImageData);
            code128.setCode(encryptedBarCodeImageData + " - " + pagenumber);




            element = code128.createImageWithBarcode(cb, null, null)

            document1.add(element);

            java.awt.Image rawImage = code128.createAwtImage(Color.BLACK, Color.WHITE);
            BufferedImage outImage = new BufferedImage(rawImage.getWidth(null), rawImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            outImage.getGraphics().drawImage(rawImage, 0, 0, null);
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ImageIO.write(outImage, "png", bytesOut);
            bytesOut.flush();
            byte[] pngImageData = bytesOut.toByteArray();

            img2 = Image.getInstance(rawImage, Color.BLACK);

            FileOutputStream fos = new FileOutputStream("c:/BarCodeQRCodeGenerator/barcode123.png");
            fos.write(pngImageData);
            fos.close();

            document1.close();

            img2.setAbsolutePosition((rect.getRight() - 450) as float, rect.getTop());
            writer.getDirectContent().addImage(img2);


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, header[0], rect.getRight(), rect.getTop(), 0);
//
//        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("page %d", pagenumber)), (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
    }
}




