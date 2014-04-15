package demo
import com.lowagie.text.*
import com.lowagie.text.pdf.PdfPageEventHelper
import com.lowagie.text.pdf.PdfWriter
/** Inner class to add a header and a footer. */
class HeaderFooter extends PdfPageEventHelper {

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

        System.out.println(writer.getPageNumber() + " - " + writer.getPageNumber() % 2);
        Image img;
        try {
            img = Image.getInstance("C:/BarCodeQRCodeGenerator/barcode.png");
            img.setAbsolutePosition((rect.getRight() - 130) as float, rect.getTop());
            writer.getDirectContent().addImage(img);
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