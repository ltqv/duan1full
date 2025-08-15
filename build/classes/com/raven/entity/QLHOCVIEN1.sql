CREATE DATABASE QLHOCVIEN1;

USE QLHOCVIEN1;

CREATE TABLE NguoiDung (
    id NVARCHAR(10) PRIMARY KEY,
    ten_dang_nhap NVARCHAR(40) UNIQUE NOT NULL,
    mat_khau NVARCHAR(40) NOT NULL,
    ho_ten NVARCHAR(40),
    so_dien_thoai NVARCHAR(10),
    email NVARCHAR(30),
    vai_tro NVARCHAR(20) CHECK (vai_tro IN ('HOC_VIEN', 'GIAO_VIEN', 'QUAN_TRI')),
    ngay_tao DATETIME DEFAULT GETDATE(),
);

CREATE TABLE KhoaHoc (
    id NVARCHAR(10) PRIMARY KEY,
    ten_khoa_hoc NVARCHAR(30),
    ngay_tao DATETIME DEFAULT GETDATE()
);

CREATE TABLE LopHoc (
    id NVARCHAR(10) PRIMARY KEY,
    id_khoa_hoc NVARCHAR(10),
    hoc_ky NVARCHAR(5),
    tong_buoi INT,
    id_giang_vien NVARCHAR(10),
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
    FOREIGN KEY (id_khoa_hoc) REFERENCES KhoaHoc(id),
    FOREIGN KEY (id_giang_vien) REFERENCES NguoiDung(id),
);

CREATE TABLE HocVienTrongLop (
    id INT PRIMARY KEY IDENTITY,
    id_lop NVARCHAR(10),
    id_hoc_vien NVARCHAR(10),
    trang_thai BIT,
	FOREIGN KEY (id_lop) REFERENCES LopHoc(id),
	FOREIGN KEY (id_hoc_vien) REFERENCES NguoiDung(id)
);

CREATE TABLE LichHoc (
    id NVARCHAR(10) PRIMARY KEY,
    id_lop NVARCHAR(10),
    thu_trong_tuan INT,
    gio_bat_dau TIME,
    gio_ket_thuc TIME,
    phong_hoc NVARCHAR(10),
    FOREIGN KEY (id_lop) REFERENCES LopHoc(id)
);

CREATE TABLE BaiKiemTra (
    id NVARCHAR(10) PRIMARY KEY,
    tieu_de VARCHAR(40),
    ngay_tao DATETIME,
    thoi_luong INT,
    tong_so_cau INT,
    thoi_gian_bat_dau TIME,
    thoi_gian_ket_thuc TIME,
    kich_hoat BIT DEFAULT 0,
);

CREATE TABLE CauHoi (
    id NVARCHAR(10) PRIMARY KEY,
    so_thu_tu INT,
    id_bai_kt NVARCHAR(10),
    noi_dung NVARCHAR(255),
    dap_an_a NVARCHAR(255),
    dap_an_b NVARCHAR(255),
    dap_an_c NVARCHAR(255),
    dap_an_d NVARCHAR(255),
    dap_an_dung NVARCHAR(1)
    FOREIGN KEY (id_bai_kt) REFERENCES BaiKiemTra(id)
);

CREATE TABLE BaiLam (
    id NVARCHAR(10) PRIMARY KEY,
    id_giang_vien NVARCHAR(10),
    id_bai_kt NVARCHAR(10),
    thoi_gian_nop DATETIME,
    ket_qua_json NVARCHAR(MAX),
    so_cau_dung INT,
    FOREIGN KEY (id_bai_kt) REFERENCES BaiKiemTra(id),
	FOREIGN KEY (id_giang_vien) REFERENCES NguoiDung(id)
);

CREATE TABLE HocVien_xembai (
	id INT PRIMARY KEY IDENTITY,
	id_hocvien NVARCHAR(10),
	id_bailam NVARCHAR(10),
	FOREIGN KEY (id_hocvien) REFERENCES NguoiDung(id),
	FOREIGN KEY (id_bailam) REFERENCES BaiLam(id)
);

CREATE TABLE LichSuHocTap (
    id INT PRIMARY KEY IDENTITY,
    id_hoc_vien NVARCHAR(10),
    id_lop NVARCHAR(10),
    id_lich_hoc NVARCHAR(10),
    ngay_hoc_thuc_te DATE,
    co_mat BIT,
    ghi_chu NVARCHAR(255),
    FOREIGN KEY (id_hoc_vien) REFERENCES NguoiDung(id),
    FOREIGN KEY (id_lop) REFERENCES LopHoc(id),
    FOREIGN KEY (id_lich_hoc) REFERENCES LichHoc(id)
);

CREATE TABLE Diem (
    id NVARCHAR(10) PRIMARY KEY,
    id_bai_kiem_tra NVARCHAR(10) UNIQUE, -- mỗi bài làm chỉ có một điểm
    diem FLOAT CHECK (diem >= 0 AND diem <= 10),
    nhan_xet NVARCHAR(255),
    ngay_cham DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_bai_kiem_tra) REFERENCES BaiKiemTra(id)
);

CREATE TABLE HocPhi (
    id_hoc_phi INT IDENTITY(1,1) PRIMARY KEY,
    id_hoc_vien NVARCHAR(10) NOT NULL,
    hoc_ky NVARCHAR(10) NOT NULL,
    nam_hoc NVARCHAR(10) NOT NULL,
    tong_hoc_phi DECIMAL(18, 2) NOT NULL,
    da_dong DECIMAL(18, 2) NOT NULL DEFAULT 0,
    con_lai AS (tong_hoc_phi - da_dong) PERSISTED,
    trang_thai NVARCHAR(20) CHECK (trang_thai IN ('DA_DONG', 'CON_NO')),
    ngay_dong_cuoi DATE,
    FOREIGN KEY (id_hoc_vien) REFERENCES NguoiDung(id)
);







INSERT INTO NguoiDung VALUES
('GV01', 'gv_nguyen', '123456', N'Nguyễn Văn A', '0909111222', 'gv.a@example.com', 'GIAO_VIEN', '2025-07-29 08:30:00'),
('GV02', 'gv_tran', '123456', N'Trần Thị B', '0909222333', 'gv.b@example.com', 'GIAO_VIEN', '2025-07-29 08:35:00'),
('HV01', 'hv_le', '123456', N'Lê Minh C', '0909333444', 'hv.c@example.com', 'HOC_VIEN', '2025-07-29 09:00:00'),
('HV02', 'hv_hoang', '123456', N'Hoàng Văn D', '0909444555', 'hv.d@example.com', 'HOC_VIEN', '2025-07-29 09:05:00'),
('AD01', 'admin', 'admin123', N'Quản Trị Viên', '0909555666', 'admin@example.com', 'QUAN_TRI', '2025-07-29 09:30:00'),
('GV03', 'gv_vinh','13112004',N'Lê Thị Mẹc','0899099789','vihnle@gmail.com','GIAO_VIEN','2025-08-19 08:30:00');



INSERT INTO KhoaHoc VALUES
('KH01', N'Tiếng Anh Giao Tiếp', GETDATE()),
('KH02', N'Tiếng Anh Cơ Bản', GETDATE());

INSERT INTO LopHoc VALUES
('LH01', 'KH01', 'HK1', 20, 'GV01', '2025-08-01', '2025-10-01'),
('LH02', 'KH01', 'HK2', 20, 'GV02', '2025-08-10', '2025-10-10'),
('LH03', 'KH02', 'HK1', 15, 'GV01', '2025-08-05', '2025-09-25');

INSERT INTO HocVienTrongLop (id_lop, id_hoc_vien, trang_thai) VALUES
('LH01', 'HV01', 1),
('LH01', 'HV02', 1),
('LH02', 'HV01', 0);

INSERT INTO LichHoc VALUES
('LHOC01', 'LH01', 2, '08:00:00', '10:00:00', N'P101'),
('LHOC02', 'LH01', 4, '08:00:00', '10:00:00', N'P101'),
('LHOC03', 'LH02', 3, '10:00:00', '12:00:00', N'P202');

INSERT INTO BaiKiemTra VALUES
('BKT01', N'Bài kiểm tra số 1', GETDATE(), 30, 3, '08:00', '2025-08-15 08:30', 1),
('BKT02', N'Bài kiểm tra số 2', GETDATE(), 45, 4, '09:00', '2025-08-20 09:45', 0);

INSERT INTO CauHoi VALUES
('CH01', 1, 'BKT01', N'What is your name?', N'Alice', N'Bob', N'Charlie', N'David', 'A'),
('CH02', 2, 'BKT01', N'How are you?', N'Fine', N'Not fine', N'Good', N'Okay', 'C'),
('CH03', 3, 'BKT01', N'Where do you live?', N'Hanoi', N'Hue', N'Da Nang', N'Saigon', 'D');

INSERT INTO BaiLam VALUES
('BL01', 'GV01', 'BKT01', '2025-08-15 08:25', N'{"CH01":"A","CH02":"C","CH03":"D"}', 3),
('BL02', 'GV01', 'BKT01', '2025-08-15 08:27', N'{"CH01":"B","CH02":"C","CH03":"A"}', 1),

('BL03', 'GV02', 'BKT02', '2025-08-15 08:30', N'{"CH01":"D","CH02":"A","CH03":"C"}', 2),
('BL04', 'GV03', 'BKT01', '2025-08-15 08:35', N'{"CH01":"C","CH02":"B","CH03":"D"}', 3);


INSERT INTO HocVien_xembai (id_hocvien, id_bailam) VALUES
('HV01', 'BL01'),
('HV02', 'BL02');

INSERT INTO LichSuHocTap (id_hoc_vien, id_lop, id_lich_hoc, ngay_hoc_thuc_te, co_mat, ghi_chu)
VALUES 
('HV01', 'LH01', 'LHOC01', '2025-08-01', 1, N'Có mặt đúng giờ'),
('HV01', 'LH01', 'LHOC02', '2025-08-03', 0, N'Nghỉ không phép'),
('HV02', 'LH01', 'LHOC01', '2025-08-01', 1, N''),
('HV02', 'LH01', 'LHOC02', '2025-08-03', 1, N'Đi trễ 10 phút');

INSERT INTO Diem (id, id_bai_kiem_tra, diem, nhan_xet, ngay_cham) VALUES
('D001', 'BKT01', 8.5, N'Bài làm tốt, cần chú ý trình bày.', '2025-08-01 09:00:00'),
('D002', 'BKT02', 6.0, N'Làm chưa đầy đủ, cần ôn tập lại.', '2025-08-01 10:30:00');

-- Thêm học phí cho học viên khác HV002
INSERT INTO NguoiDung (id, ten_dang_nhap, mat_khau, ho_ten, so_dien_thoai, email, vai_tro)
VALUES
('HV002', 'hocvien2', '123456', N'Lê Văn C', '0911222333', 'levanc@example.com', 'HOC_VIEN');

INSERT INTO HocPhi (id_hoc_vien, hoc_ky, nam_hoc, tong_hoc_phi, da_dong, trang_thai, ngay_dong_cuoi)
VALUES
('HV002', 'HK1', '2025', 4500000, 4500000, 'DA_DONG', '2025-07-25');





SELECT * FROM NguoiDung WHERE ten_dang_nhap = 'admin';


USE master;
GO

ALTER DATABASE QLHOCVIEN1 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO

DROP DATABASE QLHOCVIEN1;

select * from HocPhi;
select * from nguoidung;