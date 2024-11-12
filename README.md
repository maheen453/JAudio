# 🎵 JAudio

**A Java-based music application for managing songs, audiobooks, with features like library management, dynamic playlist creation, and audio content downloads.** The project emphasizes a strong foundation in object-oriented programming (OOP) principles: abstraction, inheritance, polymorphism, and encapsulation.

## 🛠️ Object-Oriented Design
### Key OOP Concepts

- **Abstraction**:  
  Abstract classes for media content provide a flexible framework, making it simple to extend the hierarchy.
  - Example: `AudioContent` is an abstract class, and `Song`, `Audiobook`, and `Podcast` extend this to define specific behaviors.

- **Inheritance and Polymorphism**:  
  Unified behavior for `play` and `download` methods across different content types, with specialized functionality for each media type.
  - Example: Methods like `play()` and `download()` are implemented in subclasses, ensuring different media content behaves accordingly.

- **Encapsulation**:  
  Data security ensured by using private fields with public methods for controlled access to audio content properties.
  - Example: `AudioContent` stores fields like `title`, `artist`, and `genre` as private, providing public getters and setters for safe access.


## 📌 Features

### 📚 Library Management
- **Display Stored Content, Songs, Audiobooks, Artists, Playlists**: View all available media content and playlists at a glance.
  - Commands: `store`, `songs`, `books`, `artists`, `playlists`

![image](https://github.com/user-attachments/assets/19ab4241-4381-4ba9-adc9-5f726ac7cc5f)

### ⬇️ Audio Content Download
- **Download by List Numbers**: Select and download multiple audio contents by specifying their numbers (e.g., download 4 to 7).
- **Download by Artist**: Fetch and download all songs/audiobooks from a specific artist.
- **Download by Genre**: Retrieve and download audio content by genre.
  - Commands: `download`, `downloada`, `downloadg`

![image](https://github.com/user-attachments/assets/f2c04add-99ba-4be9-934f-aa326007cdde)
![image](https://github.com/user-attachments/assets/e24043cb-765c-4570-bea0-2b93e73d8f10)
![image](https://github.com/user-attachments/assets/1837c5db-96ad-4e08-af14-4dbcaaa06b0f)

### 🔍 Search
- **Search by Title**: Locate audio content by its title.
- **Search by Artist**: Find all media items created by a specific artist.
- **Search by Genre**: Browse through audio content based on genre.
  - Commands: `search`, `searcha`, `searchg`

![image](https://github.com/user-attachments/assets/452aea87-bc2d-48b7-9e0a-f324856140a5)

![image](https://github.com/user-attachments/assets/38e8a793-1ffb-4ca6-b41f-02d5bb3d55ad)

![image](https://github.com/user-attachments/assets/a302f181-2288-407c-aaf7-b1e0e5710978)


### 🎶 Songs
- **Play Song Lyrics and Information**: Display lyrics and additional details for selected songs.
- **Delete Song from Downloads**: Remove a song from your downloaded content.
  - Commands: `playsong`, `delsong`

![image](https://github.com/user-attachments/assets/0dd5a87c-f43f-4e56-b19b-95528d27f90a)

![image](https://github.com/user-attachments/assets/05f03442-43f0-4a2d-af38-22d8cffaafc9)



### 📖 Audio Books
- **Play an Audiobook and Chapter**: Listen to an audiobook by printing the text of a specific chapter.
- **Display Audiobook and Chapters**: View the table of contents and chapters of an audiobook.
  - Commands: `playbook`, `booktoc`
  
 ![image](https://github.com/user-attachments/assets/b71dd723-6411-46d2-abdd-ea3a62dc9fa8)

 ![image](https://github.com/user-attachments/assets/10488547-5a59-401e-a76a-45fd76bf4d4e)


### 🎧 Playlists
- **Make Playlist and Set Title**: Create a new playlist and assign it a title.
- **Add Song/Audiobook to Playlist by Title**: Include specific media items in your playlist.
- **Print All Songs/Audiobooks**: Display all media content within the playlist, including their details.
- **Play All Songs/Audiobooks**: "Play" all items in a playlist by printing their lyrics or text.
- **Delete Song/Audiobook from Playlist**: Remove items from your playlist.
  - Commands: `makepl`, `addtopl`, `printpl`, `playpl`, `delfrompl`

![image](https://github.com/user-attachments/assets/40d77c2b-500f-4c9f-8541-706f533bb4fb)

![image](https://github.com/user-attachments/assets/aa53c8f4-7819-4df3-ac3b-c749f9d09d08)

![image](https://github.com/user-attachments/assets/f3f9f5c4-f600-4d9a-bfe5-b7d68d5323f0)


### 🔄 Sorting
- **Sort by Year**: Organize content in the library by release year.
- **Sort by Name**: Alphabetically sort audio content.
- **Sort by Length**: Order content by duration.
  - Commands: `sortbyyear`, `sortbyname`, `sortbylength`

![image](https://github.com/user-attachments/assets/a3d70a16-4022-467e-9f05-06f4e9deb2ca)



