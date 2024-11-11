import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
	private ArrayList<AudioContent> contents;
	// create three maps for searching title, artists and genre
	private HashMap<String, Integer> byTitle;
	private HashMap<String, ArrayList<Integer>> byArtist;
	private HashMap<String, ArrayList<Integer>> byGenre;

	public AudioContentStore() {
		
		byTitle = new HashMap<String, Integer>();
		byArtist = new HashMap<String, ArrayList<Integer>>();
		byGenre = new HashMap<String, ArrayList<Integer>>();
		contents = new ArrayList<AudioContent>();
		// calling methods
		fillContents("store.txt");
		fillArtists();
		fillGenres();
		fillTitles();

	}

		private void fillContents(String filename) {
		// for file io exception
		try {
			Scanner in = new Scanner(new File(filename));
			while (in.hasNextLine()) {
				// storting general information for audiocontents
				String contentType = in.nextLine();
				String id = in.nextLine();
				String title = in.nextLine();

				int year = in.nextInt();
				int length = in.nextInt();
				in.nextLine();

				String artist = in.nextLine();
				String composer = in.nextLine();
				if (contentType.equalsIgnoreCase("SONG")) {
					String genre = in.nextLine();
					Song.Genre realGenre = null;
					switch (genre) {
						case "POP":
							realGenre = Song.Genre.POP;
							break;
						case "ROCK":
							realGenre = Song.Genre.ROCK;
							break;
						case "JAZZ":
							realGenre = Song.Genre.JAZZ;
							break;
						case "CLASSICAL":
							realGenre = Song.Genre.CLASSICAL;
							break;
						case "HIPHOP":
							realGenre = Song.Genre.HIPHOP;
							break;
						case "RAP":
							realGenre = Song.Genre.RAP;
							break;
					}
					//getting lyrics
					int lines = in.nextInt();
					String music = "";
					for (int i = 0; i <= lines; i++) {
						music += in.nextLine() + "\n";
					}
					// adding song content and creating song object
					contents.add(
							new Song(title, year, id, contentType, music, length, artist, composer, realGenre, music));
				}

				else if (contentType.equalsIgnoreCase("AUDIOBOOK")) {
					int numTitles = in.nextInt();
					in.nextLine();
					ArrayList<String> chapters = new ArrayList<String>();
					ArrayList<String> chaptersTitles = new ArrayList<String>();
					for (int i = 0; i < numTitles; i++) {
						chaptersTitles.add(in.nextLine());
					}
					String contentText = "";

					for (int i = 0; i < numTitles; i++) {
						int numLines = in.nextInt();
						in.nextLine();

						for (int j = 0; j < numLines; j++) {

							contentText += in.nextLine();

						}
						chapters.add(contentText);
						contentText = "";

					}
					contents.add(new AudioBook(title, year, id, contentType, "", length, artist, composer,
							chaptersTitles, chapters));
				}
			}
			in.close();
		}

		catch (FileNotFoundException e) {
			System.out.println(filename + " Not Found");
		}

	}


	private void fillArtists() {
		ArrayList<Integer> numArtists = new ArrayList<Integer>();
		String name = "";
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).getType().equalsIgnoreCase("SONG")) {
				Song s = (Song) contents.get(i);
				name = s.getArtist();
				// add song index
				numArtists.add(i + 1);
			}
			// same as above but for audiobook
			else if (contents.get(i).getType().equalsIgnoreCase("AUDIOBOOK")) {
				AudioBook a = (AudioBook) contents.get(i);
				name = a.getAuthor();
				numArtists.add(i + 1);
			}
			// checks if the artist name is already in hashmap
			if (!byArtist.containsKey(name)) {
				// puts name and song indexes into hashmap
				byArtist.put(name, numArtists);
			} else {
				// adding song num if artist already in hashmap
				numArtists = byArtist.get(name);
				numArtists.add(i + 1);
				byArtist.put(name, numArtists);
			}
			// reset index nums list
			numArtists = new ArrayList<Integer>();
		}
	}


	public void fillGenres() {
		ArrayList<Integer> numGenre = new ArrayList<Integer>();
		String genre = "";
		for (int i = 0; i < contents.size(); i++) {
			// only songs have genres
			if (contents.get(i).getType().equalsIgnoreCase("SONG")) {
				Song s = (Song) contents.get(i);
				genre = s.getGenre().toString();
				numGenre.add(i + 1);
				if (!byGenre.containsKey(genre)) {
					byGenre.put(genre, numGenre);
				} else {
					numGenre = byGenre.get(genre);
					numGenre.add(i + 1);
					byGenre.put(genre, numGenre);
				}
			}
			numGenre = new ArrayList<Integer>();
		}
	}


	private void fillTitles() {
		String title = "";
		int num = 0;
		for (int i = 0; i < contents.size(); i++) {
			// check for song
			if (contents.get(i).getType().equalsIgnoreCase("SONG")) {
				// creates song, gets title and index
				Song s = (Song) contents.get(i);
				title = s.getTitle();
				num = i + 1;
			}
			// same as above but for audiobook
			else if (contents.get(i).getType().equalsIgnoreCase("AUDIOBOOK")) {
				AudioBook a = (AudioBook) contents.get(i);
				title = a.getTitle();
				num = i + 1;
			}
			// checks if the title is not already downloaded, and adds to the hashmap
			if (!byTitle.containsKey(title)) {
				byTitle.put(title, num);
			}
		}
	}

	public void printGenreSongs(String genre) {
		//if content does not exist
		if (!byGenre.containsKey(genre)) {
			throw new contentDNE(genre);
		}
		// gets int list for index of songs from genre
		ArrayList<Integer> numGenre = byGenre.get(genre);
		// loops and prints song info
		for (int i = 0; i < numGenre.size(); i++) {
			System.out.print((numGenre.get(i)) + ". ");
			contents.get(numGenre.get(i) - 1).printInfo();
			System.out.println();
		}
	}

	public void printArtistSongs(String name) {
		//if content does not exist
		if (!byArtist.containsKey(name)) {
			throw new contentDNE(name);
		}
		// list for index of songs from artist
		ArrayList<Integer> numSongs = byArtist.get(name);
		for (int i = 0; i < numSongs.size(); i++) {

			System.out.print((numSongs.get(i)) + ". ");
			contents.get(numSongs.get(i) - 1).printInfo();
			System.out.println();
		}
	}

	// prints out song based on given title
	public void printContentTitle(String title) {
		//if content does not exist
		if (!byTitle.containsKey(title)) {
			throw new contentDNE(title);
		}
		// gets song index from titles
		int numContent = byTitle.get(title);
		// prints song info
		System.out.print(numContent + ". ");
		contents.get(numContent - 1).printInfo();
	}

	public ArrayList<Integer> getArtistIndex(String name) {
		// if content does not exist
		if (!byArtist.containsKey(name)) {
			throw new contentDNE(name);
		}
		return byArtist.get(name);
	}

	public ArrayList<Integer> getGenreIndex(String genre) {
		// if content does not exist
		if (!byGenre.containsKey(genre)) {
			throw new contentDNE(genre);
		}
		return byGenre.get(genre);

	}

	public AudioContent getContent(int index) {
		if (index < 1 || index > contents.size()) {
			return null;
		}
		return contents.get(index - 1);
	}

	public void listAll() {
		for (int i = 0; i < contents.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}
}