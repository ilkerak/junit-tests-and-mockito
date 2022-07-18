package com.example.mockito;

import com.example.mockito.model.Artist;
import com.example.mockito.repository.ArtistRepository;
import com.example.mockito.service.ArtistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.Random.class)
@SpringBootTest
class MockitoApplicationTests {

    private Artist artist;
    private Artist artist2;

    @MockBean
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistService artistService;

    @BeforeEach
    void init () {
        artist = new Artist();
        artist.setId(1L);
        artist.setName("Eminem");
        artist.setSpotifyAddress("https://open.spotify.com/artist/7dGJo4pcD2V6oG8kP0tJRR");

        artist2 = new Artist();
        artist2.setId(2L);
        artist2.setName("Kanye West");
        artist2.setSpotifyAddress("https://open.spotify.com/artist/5K4W6rqBFWDnAN6FQUkS6x");

    }

    @DisplayName("Save Artist")
    @Test
    void testSaveArtist() {

        when (artistRepository.save(artist)).thenReturn(artist);

        Artist saved = artistService.save(artist);

        assertNotNull(saved);

        verify (artistRepository, times(1)).save(artist);

    }

    @DisplayName("Get Artist By ID")
    @Test
    void testGetArtistById () {

        when(artistRepository.findById(1L)).thenReturn(Optional.ofNullable(artist));

        assertNotNull(artistService.getArtistById(1L));

        verify(artistRepository, times(1)).findById(1L);

    }

    @DisplayName("Get All Artists")
    @Test
    void testGetAllArtists () {

        when(artistRepository.findAll()).thenReturn(List.of(artist, artist2));

        assertEquals(2, artistService.getAllArtists().size());

        verify(artistRepository, times(1)).findAll();

    }

    @DisplayName("Get an Exception")
    @Test
    void testGetException () {

        when(artistRepository.findById(3L)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, ()-> artistService.getArtistById(3L));

        verify(artistRepository, times(1)).findById(3L);

    }

}
