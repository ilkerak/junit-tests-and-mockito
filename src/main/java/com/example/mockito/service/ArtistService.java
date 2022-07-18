package com.example.mockito.service;

import com.example.mockito.model.Artist;
import com.example.mockito.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public Artist save (Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist getArtistById (long id) {
        return artistRepository.findById(id).orElseThrow();
    }

    public List<Artist> getAllArtists () {
        return artistRepository.findAll();
    }

}
