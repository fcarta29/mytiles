package com.byteknowledge.mytiles.data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.TileBagDao;
import com.byteknowledge.mytiles.dao.TileBoardDao;
import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.dao.TilePlacementDao;
import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.dto.TileBagDto.TileBagBuilder;
import com.byteknowledge.mytiles.dto.TileBoardDto;
import com.byteknowledge.mytiles.dto.TileBoardDto.TileBoardBuilder;
import com.byteknowledge.mytiles.dto.TileCoordinatesDto;
import com.byteknowledge.mytiles.dto.TileDto.TileBuilder;
import com.byteknowledge.mytiles.dto.TilePlacementDto.TilePlacementBuilder;
import com.byteknowledge.mytiles.dto.UserDto.UserBuilder;
import com.byteknowledge.mytiles.model.Tile;
import com.byteknowledge.mytiles.model.TileBag;
import com.byteknowledge.mytiles.model.TileBoard;
import com.byteknowledge.mytiles.model.TilePlacement;
import com.byteknowledge.mytiles.model.User;

@RestController
@CrossOrigin
@RequestMapping("/tileboards/view")
public class TileBoardViewRestController {

    private static final Logger LOG = Logger.getLogger(TileBoardViewRestController.class);

    @Autowired
    private TileBoardDao tileBoardDao;

    @Autowired
    private TileBagDao tileBagDao;

    @Autowired
    private TileDao tileDao;

    @Autowired
    private TilePlacementDao tilePlacementDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody TileBoardDto getTileBoardView(@PathVariable("id") final UUID tileBoardId) {
        final TileBoard tileBoard = tileBoardDao.get(tileBoardId);
        final User creator = userDao.get(tileBoard.getCreatorId());
        final User owner = userDao.get(tileBoard.getOwnerId());
        final List<User> participants = userDao.getList(tileBoard.getParticipantIds());
        final TileBag tileBag = tileBagDao.get(tileBoard.getTileBagId());
        final List<Tile> tiles = tileDao.getList(tileBag.getTiles());
        final List<TilePlacement> tilePlacements = tilePlacementDao.list(tileBoardId, tileBag.getId());
        final Map<UUID,TilePlacement> tilePlacementMap = new HashMap<UUID,TilePlacement>();
        LOG.info("tilePlacement in before map");
        for (final TilePlacement tilePlacement : tilePlacements) {
            tilePlacementMap.put(tilePlacement.getTileId(), tilePlacement);
            LOG.info("tp in map: " + tilePlacement.getTileId() + " tp:" + tilePlacement);
        }
        LOG.info("tilePlacement in after map");

        LOG.info("tileBoard: " + tileBoard);
        LOG.info("creator: " + creator);
        LOG.info("owner: " + owner);
        LOG.info("participants: " + participants);
        LOG.info("tileBag: " + tileBag);
        LOG.info("tiles: " + tiles);
        LOG.info("tilePlacements: " + tilePlacements);

        // build view object
        final TileBoardBuilder tileBoardBuilder = new TileBoardBuilder(
                new UserBuilder(creator.getUserName())
                        .setId(creator.getId().toString())
                        .setFirstName(creator.getFirstName())
                        .setLastName(creator.getLastName()),
                new UserBuilder(owner.getUserName())
                        .setId(owner.getId().toString())
                        .setFirstName(owner.getFirstName())
                        .setLastName(owner.getLastName()),
                tileBoard.getName());
        // set tile bag
        final TileBagBuilder tileBagBuilder = new TileBagBuilder(tileBag.getName())
                .setId(tileBag.getId().toString())
                .setCreatedTime(tileBag.getCreatedTime().toString());
        for (final Tile tile : tiles) {
            LOG.info("In loop tile: " + tile.getId());
            TilePlacement tilePlacement = tilePlacementMap.get(tile.getId());
            LOG.info("In loop tilePlacement: " + tilePlacement);
            // check this incase the tile has never been moved
            if (tilePlacement == null) {
                tilePlacement = new TilePlacement();
                LOG.info("In new loop tilePlacement: " + tilePlacement);
            }
            tileBagBuilder.addTile(new TileBuilder(tile.getLabel())
                    .setId(tile.getId().toString())
                    .setCreatedTime(tile.getCreatedTime().toString())
                    .setPlacement(new TilePlacementBuilder(
                            new TileCoordinatesDto(tilePlacement.getX(),tilePlacement.getY(),tilePlacement.getZ()))
                                    .setlastUpdatedTime(tilePlacement.getLastUpdatedTime().toString())
                                    .setLastMovedById(tilePlacement.getLastMovedById())));
        }
        tileBoardBuilder.setTileBag(tileBagBuilder);
        // add all participants
        for (final User participant : participants) {
            tileBoardBuilder.addParticipant(new UserBuilder(participant.getUserName())
                    .setId(participant.getId().toString())
                    .setFirstName(participant.getFirstName())
                    .setLastName(participant.getLastName()));
        }
        tileBoardBuilder
                .setId(tileBoard.getId().toString())
                .setCreatedTime(tileBoard.getCreatedTime().toString())
                .setLastUpdatedTime(tileBoard.getLastUpdatedTime().toString());
        return tileBoardBuilder.build();
    }
}
