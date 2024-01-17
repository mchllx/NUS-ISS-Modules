package sg.edu.nus.iss.d26.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.d26.models.Game;
import sg.edu.nus.iss.d26.repository.CommentRepository;
import sg.edu.nus.iss.d26.repository.GameRepository;

import java.util.List;

@Service
public class SearchGameService {

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private CommentRepository commentRepo;

    //proxy search method
    public List<Game> searchGame(Integer limit, Integer offset) {
        return gameRepo.search(limit, offset);
    }

    public List<Game> getGamesByRank() {
        return gameRepo.getGamesByRank();
    }

    public Game getGameDetails(Integer gid) {
        return gameRepo.getGameDetails(gid);
    }



    
}
