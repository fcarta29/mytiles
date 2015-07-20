package com.byteknowledge.mytiles.data;

import java.util.Calendar;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.byteknowledge.mytiles.dao.TileBagDao;
import com.byteknowledge.mytiles.dao.TileBoardDao;
import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.model.Tile;
import com.byteknowledge.mytiles.model.TileBag;
import com.byteknowledge.mytiles.model.TileBoard;
import com.byteknowledge.mytiles.model.User;
import com.byteknowledge.mytiles.service.Service;

@SpringBootApplication
@ComponentScan("com.byteknowledge.mytiles")
public class MyTilesDataApplication {
    
    private final static Logger LOG = Logger.getLogger(MyTilesDataApplication.class);

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(MyTilesDataApplication.class, args);
        
        final UserDao userDao = (UserDao) ctx.getBean("userDao");
        
        final User user = new User();
        user.setId(UUID.fromString(Service.USER1_ID));
        user.setUserName("fcarta");
        user.setFirstName("Frank");
        user.setLastName("Carta");
        userDao.save(user);

        final User user2 = new User();
        user2.setId(UUID.fromString(Service.USER2_ID));
        user2.setUserName("awerhane");
        user2.setFirstName("Andrew");
        user2.setLastName("Werhane");
        userDao.save(user2);
        
        final TileDao tileDao = (TileDao) ctx.getBean("tileDao");
        
        final Tile tile1 = new Tile();
        tile1.setId(UUID.fromString(Service.TILE1_ID));
        tile1.setCreatorId(user.getId());
        tile1.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        tile1.setLabel("Halarious");
        tileDao.save(tile1);
        
        final Tile tile2 = new Tile();
        tile2.setId(UUID.fromString(Service.TILE2_ID));
        tile2.setCreatorId(user2.getId());
        tile2.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        tile2.setLabel("Funny");    
        tileDao.save(tile2);
        
        final TileBagDao tileBagDao = (TileBagDao) ctx.getBean("tileBagDao");
        
        final TileBag tileBag = new TileBag();
        tileBag.setId(UUID.fromString(Service.TILEBAG_ID));
        tileBag.setCreatorId(user.getId());
        tileBag.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        tileBag.setName("Happy FunTimes Bag - Halarious words");
        tileBag.getTiles().add(tile1);
        tileBag.getTiles().add(tile2);
        tileBagDao.save(tileBag);
        
        final TileBoardDao tileBoardDao = (TileBoardDao) ctx.getBean("tileBoardDao");
        
        final TileBoard tileBoard = new TileBoard();
        tileBoard.setId(UUID.fromString(Service.TILEBOARD_ID));
        tileBoard.setCreatorId(user.getId());
        tileBoard.setOwnerId(user.getId());
        tileBoard.setTileBagId(tileBag.getId());
        tileBoard.setName("Funny things board.");
        tileBoard.getParticipantIds().add(user);
        tileBoard.getParticipantIds().add(user2);
        tileBoardDao.save(tileBoard);
        
        LOG.debug(userDao.get(UUID.fromString(Service.USER1_ID)));
        LOG.debug(userDao.get(UUID.fromString(Service.USER2_ID)));
        LOG.debug(userDao.get(UUID.fromString(Service.TILEBOARD_ID)));
    }
}
