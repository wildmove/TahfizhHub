## Tahfizh Hub

## Final Project Pengembangan Aplikasi Mobile

### 1. Introduction


Berawal dari kegiatan halaqah tahfizh LPTQ UMY, kami memulai inisiatif untuk mengembangkan Tahfizh Hub, suatu aplikasi yang dirancang untuk memonitor kemajuan dalam hafalan dan murajaah Al-Quran. 
Pengguna dapat dengan mudah mencatat perkembangannya, termasuk menambah, mengedit, dan menghapus data setoran hafalan serta murajaah hafalannya melalui Aplikasi Tahfizh Hub.
Aplikasi ini menggunakan Firebase Firestore sebagai Cloud Database dan Firebase Auth sebagai Fitur Autentikasi Pengguna Aplikasi, memastikan efisiensi dan keamanan dalam pengelolaan progres hafalan Al-Quran.

### 2. Database Structure
**Firebase Firestore**
- User (Collection)
    - Data User (Document)
        - Setoran (Collection)
            - Data Setoran (Document)
        - Murajaah (Collection
            - Data Murajaah (Document)

### 3. Features
- Login
- Register
- Logout
- Setoran Hafalan
  - Tambah Data Hafalan
  - Lihat Detail Data Hafalan
  - Edit Data Hafalan
  - Hapus Data Hafalan
- Murajaah
  - Tambah Data Murajaah
  - Lihat Detail Murajaah
  - Edit Data Murajaah
  - Hapus Data Murajaah

### 4. Use Case Diagram
![Use case diagram](https://github.com/wildmove/TahfizhHub/assets/89931105/4d834f23-703e-4e0d-8461-f8cec3704e08)

