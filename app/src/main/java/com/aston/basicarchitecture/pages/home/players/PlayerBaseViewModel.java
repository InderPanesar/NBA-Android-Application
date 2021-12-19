package com.aston.basicarchitecture.pages.home.players;

import androidx.lifecycle.ViewModel;

import com.aston.basicarchitecture.engine.repository.players.PlayersRepository;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlayerBaseViewModel extends ViewModel {

    PlayersRepository repository;
    @Inject
    PlayerBaseViewModel(@Named("PlayersRepository") PlayersRepository exampleRepository) {
        repository = exampleRepository;
    }

}
