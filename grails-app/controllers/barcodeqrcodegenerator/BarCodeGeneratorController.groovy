package barcodeqrcodegenerator

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.lowagie.text.Document
import com.lowagie.text.Image
import com.lowagie.text.Paragraph
import com.lowagie.text.Rectangle
import com.lowagie.text.pdf.Barcode
import com.lowagie.text.pdf.Barcode128
import com.lowagie.text.pdf.BarcodeEAN
import com.lowagie.text.pdf.PdfContentByte
import com.lowagie.text.pdf.PdfWriter

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.image.BufferedImage

class BarCodeGeneratorController {

    def index() { }

    def generateBarCode() {

        println "-----------generateBarCode-----------------"

        String RESULT = "c:/barcodes.pdf";

        // step 1
        Document document = new Document(new Rectangle(340, 842));
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();

        Image image = null

//        // EAN 13
//        document.add(new Paragraph("Barcode EAN.UCC-13"));
//        BarcodeEAN codeEAN = new BarcodeEAN();
//        codeEAN.setCode("4512345678906");
//        document.add(new Paragraph("default:"));
//        document.add(codeEAN.createImageWithBarcode(cb, null, null));
//        codeEAN.setGuardBars(false);
//        document.add(new Paragraph("without guard bars:"));
//        document.add(codeEAN.createImageWithBarcode(cb, null, null));
//        codeEAN.setBaseline(-1f);
//        codeEAN.setGuardBars(true);
//        document.add(new Paragraph("text above:"));
//        document.add(codeEAN.createImageWithBarcode(cb, null, null));
//        codeEAN.setBaseline(codeEAN.getSize());

        // CODE 128
        document.add(new Paragraph("Barcode 128"));
        Barcode128 code128 = new Barcode128();
        code128.setCode("P^132465^5");
        image = code128.createImageWithBarcode(cb, null, null)
        document.add(image);

        java.awt.Image rawImage = code128.createAwtImage(Color.BLACK, Color.WHITE);
        BufferedImage outImage = new BufferedImage(rawImage.getWidth(null), rawImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        outImage.getGraphics().drawImage(rawImage, 0, 0, null);
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ImageIO.write(outImage, "png", bytesOut);
        bytesOut.flush();
        byte[] pngImageData = bytesOut.toByteArray();

        FileOutputStream fos = new FileOutputStream("c:/barcode.png");
        fos.write(pngImageData);
        fos.close();



//
//        // EAN 8
//        document.add(new Paragraph("Barcode EAN.UCC-8"));
//        codeEAN.setCodeType(Barcode.EAN8);
//        codeEAN.setBarHeight(codeEAN.getSize() * 1.5f);
//        codeEAN.setCode("34569870");
//        document.add(codeEAN.createImageWithBarcode(cb, null, null));
//
//        // UPC E
//        document.add(new Paragraph("Barcode UPC-E"));
//        codeEAN.setCodeType(Barcode.UPCE);
//        codeEAN.setCode("03456781");
//        document.add(codeEAN.createImageWithBarcode(cb, null, null));
//        codeEAN.setBarHeight(codeEAN.getSize() * 3f);
//
//        // EANSUPP
//        document.add(new Paragraph("Bookland"));
//        document.add(new Paragraph("ISBN 0-321-30474-8"));
//        codeEAN.setCodeType(Barcode.EAN13);
//        codeEAN.setCode("9781935182610");
//        BarcodeEAN codeSUPP = new BarcodeEAN();
//        codeSUPP.setCodeType(Barcode.SUPP5);
//        codeSUPP.setCode("55999");
//        codeSUPP.setBaseline(-2);
//        BarcodeEANSUPP eanSupp = new BarcodeEANSUPP(codeEAN, codeSUPP);
//        document.add(eanSupp.createImageWithBarcode(cb, null, BaseColor.BLUE));
//
//        // CODE 128
//        document.add(new Paragraph("Barcode 128"));
//        Barcode128 code128 = new Barcode128();
//        code128.setCode("0123456789 hello");
//        document.add(code128.createImageWithBarcode(cb, null, null));
//        code128.setCode("0123456789uffffMy Raw Barcode (0 - 9)");
//        code128.setCodeType(Barcode.CODE128_RAW);
//        document.add(code128.createImageWithBarcode(cb, null, null));
//
//        // Data for the barcode :
//        String code402 = "24132399420058289";
//        String code90 = "3700000050";
//        String code421 = "422356";
//        StringBuffer data = new StringBuffer(code402);
//        data.append(Barcode128.FNC1);
//        data.append(code90);
//        data.append(Barcode128.FNC1);
//        data.append(code421);
//        Barcode128 shipBarCode = new Barcode128();
//        shipBarCode.setX(0.75f);
//        shipBarCode.setN(1.5f);
//        shipBarCode.setSize(10f);
//        shipBarCode.setTextAlignment(Element.ALIGN_CENTER);
//        shipBarCode.setBaseline(10f);
//        shipBarCode.setBarHeight(50f);
//        shipBarCode.setCode(data.toString());
//        document.add(shipBarCode.createImageWithBarcode(cb, BaseColor.BLACK,
//                BaseColor.BLUE));
//
//        // it is composed of 3 blocks whith AI 01, 3101 and 10
//        Barcode128 uccEan128 = new Barcode128();
//        uccEan128.setCodeType(Barcode.CODE128_UCC);
//        uccEan128.setCode("(01)00000090311314(10)ABC123(15)060916");
//        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
//                BaseColor.BLACK));
//        uccEan128.setCode("0191234567890121310100035510ABC123");
//        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
//                BaseColor.RED));
//        uccEan128.setCode("(01)28880123456788");
//        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
//                BaseColor.BLACK));
//
//        // INTER25
//        document.add(new Paragraph("Barcode Interleaved 2 of 5"));
//        BarcodeInter25 code25 = new BarcodeInter25();
//        code25.setGenerateChecksum(true);
//        code25.setCode("41-1200076041-001");
//        document.add(code25.createImageWithBarcode(cb, null, null));
//        code25.setCode("411200076041001");
//        document.add(code25.createImageWithBarcode(cb, null, null));
//        code25.setCode("0611012345678");
//        code25.setChecksumText(true);
//        document.add(code25.createImageWithBarcode(cb, null, null));
//
//        // POSTNET
//        document.add(new Paragraph("Barcode Postnet"));
//        BarcodePostnet codePost = new BarcodePostnet();
//        document.add(new Paragraph("ZIP"));
//        codePost.setCode("01234");
//        document.add(codePost.createImageWithBarcode(cb, null, null));
//        document.add(new Paragraph("ZIP+4"));
//        codePost.setCode("012345678");
//        document.add(codePost.createImageWithBarcode(cb, null, null));
//        document.add(new Paragraph("ZIP+4 and dp"));
//        codePost.setCode("01234567890");
//        document.add(codePost.createImageWithBarcode(cb, null, null));
//
//        document.add(new Paragraph("Barcode Planet"));
//        BarcodePostnet codePlanet = new BarcodePostnet();
//        codePlanet.setCode("01234567890");
//        codePlanet.setCodeType(Barcode.PLANET);
//        document.add(codePlanet.createImageWithBarcode(cb, null, null));
//
//        // CODE 39
//        document.add(new Paragraph("Barcode 3 of 9"));
//        Barcode39 code39 = new Barcode39();
//        code39.setCode("ITEXT IN ACTION");
//        document.add(code39.createImageWithBarcode(cb, null, null));
//
//        document.add(new Paragraph("Barcode 3 of 9 extended"));
//        Barcode39 code39ext = new Barcode39();
//        code39ext.setCode("iText in Action");
//        code39ext.setStartStopText(false);
//        code39ext.setExtended(true);
//        document.add(code39ext.createImageWithBarcode(cb, null, null));
//
//        // CODABAR
//        document.add(new Paragraph("Codabar"));
//        BarcodeCodabar codabar = new BarcodeCodabar();
//        codabar.setCode("A123A");
//        codabar.setStartStopText(true);
//        document.add(codabar.createImageWithBarcode(cb, null, null));
//
//        // PDF417
//        document.add(new Paragraph("Barcode PDF417"));
//        BarcodePDF417 pdf417 = new BarcodePDF417();
//        String text = "Call me Ishmael. Some years ago--never mind how long "
//        + "precisely --having little or no money in my purse, and nothing "
//        + "particular to interest me on shore, I thought I would sail about "
//        + "a little and see the watery part of the world.";
//        pdf417.setText(text);
//        Image img = pdf417.getImage();
//        img.scalePercent(50, 50 * pdf417.getYHeight());
//        document.add(img);
//
//        document.add(new Paragraph("Barcode Datamatrix"));
//        BarcodeDatamatrix datamatrix = new BarcodeDatamatrix();
//        datamatrix.generate(text);
//        img = datamatrix.createImage();
//        document.add(img);
//
//        document.add(new Paragraph("Barcode QRCode"));
//        BarcodeQRCode qrcode = new BarcodeQRCode("Moby Dick by Herman Melville", 1, 1, null);
//        img = qrcode.getImage();
//        document.add(img);

        // step 5
        document.close();

        render "success"

    }

    def generateQRCode() {

        println "-----------generateQRCode-----------------"

        String qrCodeData = "Pankaj-Shinde^123^2";
        String filePath = "c:/QRCode.png";
        String charset = "UTF-8"; // or "ISO-8859-1"
        int qrCodeheight = 200
        int qrCodewidth = 200

        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));

        System.out.println("QR Code image created successfully!");

        render "success"

    }

}
