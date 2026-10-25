# Lab 03 – SQLite trong Android

## 1. Nội dung bài lab

### Phần I – SQLite và danh sách User
- Tìm hiểu cách tạo cơ sở dữ liệu SQLite và bảng `users`.
- Thực hiện các thao tác mở cơ sở dữ liệu, thêm, xóa và truy vấn người dùng.
- Đọc dữ liệu và hiển thị danh sách User bằng **ListView**.

### Phần II – Quản lý Contact
- Tạo cơ sở dữ liệu `contactsManager` với bảng `contacts` gồm ID, tên và số điện thoại.
- Xây dựng các chức năng **thêm, xem, sửa, xóa** liên hệ (CRUD).
- Hiển thị danh sách Contact bằng **ListView** và hỗ trợ **nhấn giữ để xóa**.

### Phần III – Quản lý sinh viên
- Xây dựng ứng dụng quản lý danh sách sinh viên bằng **SQLite** và **RecyclerView**.
- Thực hiện các chức năng **thêm, xem, sửa, xóa** thông tin sinh viên.
- Nhấn vào một sinh viên để mở màn hình **xem thông tin chi tiết**.

## 2. Kiến thức học được

- **SQLite:** Lưu trữ dữ liệu ngay trên thiết bị Android, không cần máy chủ cơ sở dữ liệu riêng.
- **SQLiteOpenHelper:** Tạo và quản lý phiên bản cơ sở dữ liệu thông qua `onCreate()` và `onUpgrade()`.
- **SQLiteDatabase:** Thực hiện các thao tác thêm, truy vấn, cập nhật và xóa dữ liệu.
- **ContentValues:** Đóng gói dữ liệu khi thêm hoặc cập nhật bản ghi.
- **Cursor:** Đọc kết quả truy vấn từ SQLite.
- **ListView, RecyclerView và Adapter:** Hiển thị dữ liệu thành danh sách trên giao diện.
- **Activity và Intent:** Tổ chức màn hình và chuyển giữa các màn hình của ứng dụng.
- **Sự kiện người dùng:** Xử lý thao tác nhấn, nhấn giữ và cập nhật giao diện sau khi dữ liệu thay đổi.

## 3. Tổng kết

Bài lab giúp làm quen với quy trình **tạo cơ sở dữ liệu → thao tác CRUD → đọc dữ liệu → hiển thị trên giao diện Android**. Qua ba phần thực hành, có thể xây dựng các ứng dụng quản lý dữ liệu cục bộ đơn giản bằng Java và SQLite.