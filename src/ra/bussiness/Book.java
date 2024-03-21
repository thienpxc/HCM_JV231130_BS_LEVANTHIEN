package ra.bussiness;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    private static int nextBookId =1;

    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus;

    //get vs set

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Book() {

    }


    public Book(String bookName, String author, String descriptions,
                double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = nextBookId++;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.exportPrice = exportPrice;
        this.importPrice = importPrice;
        this.interest = (float) (exportPrice - importPrice);
        this.bookStatus = bookStatus;

    }

    //hien thi danh sach
    public void displayData() {
        System.out.printf("Mã sách: %d |", bookId);
        System.out.printf("Tên sách: %s |", bookName);
        System.out.printf("Tác giả: %s | \n", author);
        System.out.printf("Mô tả: %s |", descriptions);
        System.out.printf("Giá nhập: %.2f |", importPrice);
        System.out.printf("Giá xuất: %.2f | \n", exportPrice);
        System.out.printf("Lợi nhuận: %.2f |", interest);
        System.out.printf("Trạng thái: %s  \n", (bookStatus ? "Còn hàng" : "Hết hàng"));

    }

    //validate nguowi dung nhap vao
    public Book inputData(Scanner scanner) {

        System.out.println("Nhập Tên Sách (Không được để trống):");
        this.bookName = getInputFromUser(scanner, ".+");
        System.out.println("Nhập Tên Tác Giả (Không được để trống):");
        this.author = getInputFromUser(scanner, ".+");
        System.out.println("Nhập Mô tả về Cuốn Sách (ít nhất 10 ký tự):");
        this.descriptions = getInputFromUser(scanner, ".{10,}");
        System.out.println("Giá Nhập Cuốn Sách(phải lớn hơn 0):");
        this.importPrice = getDoubleInputFromUser(scanner);
        System.out.println("Giá Xuất Cuốn Sách  (phải lớn hơn lớn hơn giá nhập 20%!:");
        this.exportPrice = getDoubleInputFromUser(scanner);
        while (this.exportPrice <= 1.2 * this.importPrice) {
            System.out.println("Giá xuất phải lớn hơn 1.2 lần giá nhập. Vui lòng nhập lại:");
            this.exportPrice = getDoubleInputFromUser(scanner);
        }

        System.out.println("Nhập Trạng Thái Cuốn Sách (true or false):");
        this.bookStatus = Boolean.parseBoolean(getInputFromUser(scanner, "true|false"));
        this.interest = (float) (this.importPrice - this.exportPrice);
        return new Book(bookName, author, descriptions, importPrice, exportPrice, interest, bookStatus);

    }


    private static String getInputFromUser(Scanner scanner, String regex) {
        while (true) {
            String dataInput = scanner.nextLine();
            boolean isValid = Pattern.compile(regex).matcher(dataInput).matches();
            if (isValid) {
                return dataInput;
            }
            System.out.println("Vui long nhap lai");
        }
    }

    private static double getDoubleInputFromUser(Scanner scanner) {
        double input = -1;
        boolean isValid = false;
        do {
            String inputData = scanner.nextLine();
            if (inputData.matches("^\\d*\\.?\\d+$")) {
                input = Double.parseDouble(inputData);
                if (input > 0) {
                    isValid = true;
                } else {
                    System.out.println("Giá nhập phải lớn hơn 0. Vui lòng nhập lại:");
                }

            } else {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập lại:");
            }
        } while (!isValid);
        return input;
    }

}
