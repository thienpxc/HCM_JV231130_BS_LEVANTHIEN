package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
    private static final int MaxBooks = 100;
    private static Book[] booksArr = new Book[0];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách ");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Tìm ra sản phẩm có giá lớn thứ hai");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");
            byte inputData = Byte.parseByte(scanner.nextLine());
            switch (inputData) {
                case 1:
                    inputFromUser(scanner);
                    break;
                case 2:
                    displayData();
                    break;
                case 3:
                    inputExportPrice();
                    break;
                case 4:
                    getInputDelete(scanner);
                    break;
                case 5:
                    searchBookByKeyword(scanner);
                case 6:
//                    updateStaff(scanner);
                case 7:
                    return;
                default:
                    System.out.println("vui long lua chon lai");


            }
        }


    }
    //thay doi thong tin theo ma sach
//    public void updateStaff(Scanner scanner) {
//        //hien thi danh sach nhan vien
//        System.out.println("danh sach nhan vien hien tai la: \n");
//        byte manageLegnth = (byte) booksArr.length;
//        for (int i = 0; i < manageLegnth; i++) {
//            booksArr[i].displayData();
//        }
//        //cap nhat thong tin
//        System.out.println("Chọn vị trí cần cập nhật thông tin:");
//        String arrayLocation = scanner.nextLine(); // Nhập mã sách dưới dạng chuỗi
//
//        for (int i = 0; i < booksArr.length; i++) {
//            if (booksArr[i].getBookId().equals(arrayLocation)) { // So sánh mã sách với mã nhập vào
//                indexToUpdate = i; // Lưu chỉ số của sách cần cập nhật
//                break;
//            }
//        }
//
//    }
    //tim kiem ten or mo ta
    public static void searchBookByKeyword(Scanner scanner) {
        System.out.println("Danh sách sách hiện tại:\n");
        int bookCount = booksArr.length;
        for (int i = 0; i < bookCount; i++) {
            booksArr[i].displayData();
        }

        // Tìm kiếm theo từ khóa trong tên sách hoặc mô tả
        System.out.println("Nhập từ khóa tìm kiếm:");
        String searchTerm = scanner.nextLine().toLowerCase();

        boolean isFound = false;
        System.out.printf("Các cuốn sách tìm thấy với từ khóa '%s':\n", searchTerm);
        for (int i = 0; i < bookCount; i++) {
            // Kiểm tra xem tên sách hoặc mô tả có chứa từ khóa không
            if (booksArr[i].getBookName().toLowerCase().contains(searchTerm) ||
                    booksArr[i].getDescriptions().toLowerCase().contains(searchTerm)) {
                System.out.println(booksArr[i].toString());
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.printf("Không tìm thấy sách nào có chứa từ khóa '%s'.\n", searchTerm);
        }
    }
    //TIM KIEM SAN PHAM LON THU 2
    private static void inputExportPrice() {
        int books = booksArr.length;
        if (books < 2) {
            System.out.println("Không đủ sách để tìm giá lớn thứ hai.");
            return;
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < books; i++) {
            if (booksArr[i].getExportPrice() > max1) {
                max2 = max1;
                max1 = (int) booksArr[i].getExportPrice();
            } else if (booksArr[i].getExportPrice() > max2 && booksArr[i].getExportPrice() != max1) {
                max2 = (int) booksArr[i].getExportPrice();
            }
        }
        if (max2 == Integer.MIN_VALUE) {
            System.out.println("Không tìm thấy giá lớn thứ hai.");
        } else {
            System.out.println("Giá lớn thứ hai: " + max2);
        }
    }

    //nguoi dung nhap vao so luong
    public static void inputFromUser(Scanner scanner) {
        //nhap so luong can them
        System.out.println("Nhập số lượng sách cần Thêm");
        int moreBooks = getIntegerInputFromUser(scanner, 1, MaxBooks);
        // khai bao 1 mang moi
        int book = booksArr.length;
        Book[] newArrBook = new Book[moreBooks + book];
        // Copy dữ liệu từ mảng cũ sang mảng mới
        for (int i = 0; i < book; i++) {
            newArrBook[i] = booksArr[i];
        }
        // Nhập thông tin cho c mới và đưa vào mảng mới

        for (int i = book; i < newArrBook.length; i++) {
            System.out.println("Nhập thông tin cho cuốn sách thứ " + (i + 1) + ":");
            Book bookss = new Book();
            bookss.inputData(scanner);
            newArrBook[i] = bookss;
        }
        booksArr = newArrBook;
    }
//hien thi danh sach
    private static void displayData(){
        System.out.println("danh sach sinh vien hien tai \n");
        for (int i = 0; i < booksArr.length; i++) {
            booksArr[i].displayData();
        }
    }
    //xoa danh sach
    private static byte getInputDelete(Scanner scanner) {
        // Nhập vị trí cần xóa
        byte arrLength = (byte) booksArr.length;
        byte indexNeedDelete = getIndexFromUser(arrLength, scanner);

        // Khai báo một mảng mới
        Book[] newDeleteStudent = new Book[arrLength - 1];
        byte currentIndex = 0;


        for (int i = 0; i < arrLength; i++) {
            if (i == indexNeedDelete) {
                continue;
            }
            newDeleteStudent[currentIndex++] = booksArr[i];
        }
        booksArr = newDeleteStudent;
        return indexNeedDelete;
    }
        //nguoi dung nhap
    private static byte getIndexFromUser(byte arraySize, Scanner scanner) {
        boolean isNotValid;
        do {
            System.out.println("Vui lòng nhập vị trí bạn muốn xóa:");
            byte arrayLocation = Byte.parseByte(scanner.nextLine());
            isNotValid = arrayLocation < 0 || arrayLocation >= arraySize;
            if (isNotValid) {
                System.out.println("Vị trí nhập không hợp lệ.");
            } else {
                return arrayLocation;
            }
        } while (isNotValid);
        return -1;
    }

    private static int getIntegerInputFromUser(Scanner scanner, int min, int max) {
        int input;
        do {
            System.out.printf("VUI Lòng Nhập Số Lượng Thêm (tư %d đến %d) \n", min, max);
            while (!scanner.hasNextInt()) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập lại:");
                scanner.next();
            }
            input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.printf("Số lượng sách phải nằm trong khoảng từ (tư %d đến %d).Vui lòng nhập lại:", min, max);

            }
        } while (input < min || input > max);
        return input;
    }


}
