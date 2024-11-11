import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		// Create my music mylibrary
		Library mylibrary = new Library();
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")){
					int index1 = 0;
					int index2 = 0;
					// get first index
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt()) {
						index1 = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					// get second index
					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt()) {
						index2 = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					// loop to download content from index1 to index2
						for (int i = index1; i <= index2; i++) {
						// try catch for if content already downloaded to library
							try {
								AudioContent content = store.getContent(i);
								mylibrary.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
							} // catch for already downloaded song
							catch (SongAlreadyDownloaded e) {
								System.out.println(e.getMessage());
							} // catch for already downloaded audiobook
							catch (AudiobookAlreadyDownloaded e) {
								System.out.println(e.getMessage());
							}
							catch (contentDNE e) {
								System.out.println(e.getMessage());
								break;
							}
						}
				}


				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) {
					int index = 0;// store index of song
					System.out.print("Song Number: ");// prompt
					if (scanner.hasNextInt()) {// checks for int inputs
						index = scanner.nextInt();// stores index
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					try {
						mylibrary.playSong(index);
					}
					catch (contentDNE e) {
						System.out.println(e.getMessage());
					}
				}


				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					System.out.print("Audio Book Number: ");
					int index = 0;// to store audiobook index

					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();// stores index
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					try {
						mylibrary.printAudioBookTOC(index);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}
				}


				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int index = 0;// store index of book
					int chapter = 0;// store chapter num
					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();// save input into index
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Chapter: ");// same as above but for the chapter
					if (scanner.hasNextInt()) {
						chapter = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					try {
						mylibrary.playAudioBook(index, chapter);
					} catch (contentDNE e) {
						System.out.println(e.getMessage());
					}
				}


				// Specify a playlist title (string)
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) {
					System.out.print("Playlist Title: "); 
					String title = "";
					if (scanner.hasNextLine()) {
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					} 

					try {
						mylibrary.playPlaylist(title);
					} catch 
					(contentDNE e) {
						System.out.println(e.getMessage());
					}

				}
	
				
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) {
					System.out.print("Playlist Title: ");
					String title = "";
					if (scanner.hasNextLine()) {
						title = scanner.next();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Content Number: "); 
					int index = 0;
					if (scanner.hasNextLine()) 
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					
					try 
					{
						mylibrary.playPlaylist(title, index);
					} 
					catch (contentDNE e) 
					{
						System.out.println(e.getMessage());
					}
				}


				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) {
					int index = 0; 
					System.out.print("Library Song #: ");
					if (scanner.hasNextLine()) {
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					try 
					{
						mylibrary.deleteSong(index);
						System.out.println("Library Song #: " + index + " deleted from Library");
					} 
					catch (contentDNE e) 
					{
						System.out.println(e.getMessage());
					}
				}


				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) {
					System.out.print("Playlist Title: "); // prompt
					String title = "";// to store playlist title
					if (scanner.hasNextLine()) {// checks for input
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					} 
					try 
					{
						mylibrary.makePlaylist(title);
						System.out.println("Playlist " + title + " created");
					} 
					catch (PlaylistExists e) 
					{
						System.out.println(e.getMessage());
					}

				}


				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL")) // print playlist content
				{
					String title = "";
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine()) {
						title = scanner.next();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					try 
					{
						mylibrary.printPlaylist(title);
					} 
					catch (contentDNE e) 
					{
						System.out.println(e.getMessage());
					}
				}

				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					String title = "";
					String type = "";
					int index = 0; 
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine()) 
					{
						title = scanner.next();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Content Type [SONG, AUDIOBOOK]: ");
					if (scanner.hasNextLine()) {
						type = scanner.next();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Library Content #: ");
					if (scanner.hasNextLine()) {
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					} 

					try
					{
						mylibrary.addContentToPlaylist(type, index, title);
						System.out.println(type +  " added to playlist" + " " + title);

					} 
					catch (contentDNE e) 
					{
						System.out.println(e.getMessage());
					}
				}


				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					String title = "";// to store playlist title
					int index = 0; // store index num
					System.out.print("Playlist Title: "); // prompt
					if (scanner.hasNextLine()) 
					{
						title = scanner.next();// store the string
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					System.out.print("Playlist Content #: "); // prompt
					if (scanner.hasNextLine()) 
					{
						index = scanner.nextInt();// store the int
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					try {
						mylibrary.delContentFromPlaylist(index, title);
					} 
					catch (contentDNE e) {
						System.out.println(e.getMessage());
					}
				}


				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}


				else if (action.equalsIgnoreCase("SEARCH")) // search for audio content by title
				{
					String title = "";// to store title
					System.out.print("Title: ");
					if (scanner.hasNextLine()) {
						title = scanner.nextLine();
					}
		
					try {
						store.printContentTitle(title);
					} 
					catch (contentDNE e) {
						System.out.println(e.getMessage());
					}
				}

				else if (action.equalsIgnoreCase("SEARCHA"))//search for audio content by artist
				{
					String name = "";
					System.out.print("Artist: "); 
					if (scanner.hasNextLine()) {
						name = scanner.nextLine();
					}

					try{
						store.printArtistSongs(name);
					}
					catch(contentDNE e){
						System.out.println(e.getMessage());
					}
				}

				else if (action.equalsIgnoreCase("SEARCHG")) // search for audio content by genre
				{
					String genre = "";
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					if (scanner.hasNextLine()) {
						genre = scanner.nextLine();
					}
					
					try{
						store.printGenreSongs(genre);
					}
					catch(contentDNE e){
						System.out.println(e.getMessage());
					}
				}


				else if (action.equalsIgnoreCase("DOWNLOADA")) // download audio content by artists
				{
					String name = "";// to store name

					System.out.print("Artist Name: "); // prompt
					if (scanner.hasNextLine()) {// checks for input
						name = scanner.nextLine();// store the string
					}
					
					// get arraylist of songs by artist
					ArrayList<Integer> a = new ArrayList<Integer>();
					try{
						a = store.getArtistIndex(name);
					}catch (contentDNE e){
						System.out.println(e.getMessage());
					}
					// loop through array downloading songs
					for (int i = 0; i < a.size(); i++) {
						// try catch for if content already downloaded to library
						try {
							// grabbing content and downloading
							AudioContent content = store.getContent(a.get(i));
							mylibrary.download(content);
							// print statement for action
							System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
						} 
						catch (SongAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						} // catch for already downloaded audiobook
						catch (AudiobookAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						}
						catch (contentDNE e){
							System.out.println(e.getMessage());
						}
					}

				}


				else if (action.equalsIgnoreCase("DOWNLOADG")) // download audio content by genere
				{
					String genre = "";
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: "); 
					if (scanner.hasNextLine()) {
						genre = scanner.nextLine();
					}
					ArrayList<Integer> g = new ArrayList<Integer>();
					try{
						g = store.getGenreIndex(genre);
					}
					catch (contentDNE e){
						System.out.println(e.getMessage());
					}
					
					// loop through array downloading songs
					for (int i = 0; i < g.size(); i++) 
					{
						try {
							AudioContent content = store.getContent(g.get(i));
							mylibrary.download(content);
							System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
						}
						catch (SongAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						}
						catch (AudiobookAlreadyDownloaded e) {
							System.out.println(e.getMessage());
						}
						catch (contentDNE e){
							System.out.println(e.getMessage());
						}
					}
				}
				System.out.print("\n>");
			}
			scanner.close();
		}
}

