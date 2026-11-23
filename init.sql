
BEGIN;


CREATE TABLE users (
                       id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       role TEXT,
                       username TEXT UNIQUE,
                       displayname TEXT,
                       password_hash TEXT
);



CREATE TABLE artists (
                         id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         name TEXT NOT NULL
);



CREATE TABLE formats (
                         id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         extension TEXT NOT NULL,
                         mime TEXT NOT NULL,
                         is_lossless BOOLEAN NOT NULL
);



CREATE TABLE albums (
                        id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name TEXT NOT NULL,
                        privacy BOOLEAN NOT NULL,
                        description TEXT,
                        user_id INTEGER NOT NULL,

                        CONSTRAINT fk_albums_user
                            FOREIGN KEY (user_id)
                                REFERENCES users(id)
                                ON DELETE CASCADE
);



CREATE TABLE playlists (
                           id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           name TEXT NOT NULL,
                           visibility BOOLEAN NOT NULL,
                           user_id INTEGER,

                           CONSTRAINT fk_playlists_user
                               FOREIGN KEY (user_id)
                                   REFERENCES users(id)
                                   ON DELETE SET NULL
);


CREATE TABLE tracks (
                        id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        public_id UUID NOT NULL UNIQUE,
                        name VARCHAR(255) NOT NULL,
                        duration INTEGER,
                        bitrate INTEGER,
                        status TEXT NOT NULL,
                        storage_key TEXT NOT NULL,

                        artist_id INTEGER,
                        album_id INTEGER,
                        format_id INTEGER NOT NULL,
                        user_id INTEGER NOT NULL,

                        CONSTRAINT fk_tracks_artist
                            FOREIGN KEY (artist_id)
                                REFERENCES artists(id)
                                ON DELETE SET NULL,

                        CONSTRAINT fk_tracks_album
                            FOREIGN KEY (album_id)
                                REFERENCES albums(id)
                                ON DELETE SET NULL,

                        CONSTRAINT fk_tracks_format
                            FOREIGN KEY (format_id)
                                REFERENCES formats(id)
                                ON DELETE RESTRICT,

                        CONSTRAINT fk_tracks_user
                            FOREIGN KEY (user_id)
                                REFERENCES users(id)
                                ON DELETE CASCADE
);

CREATE TABLE playlist_tracks (
                        playlist_id INTEGER NOT NULL
                            REFERENCES playlists(id) ON DELETE CASCADE,

                        track_id INTEGER NOT NULL
                            REFERENCES tracks(id) ON DELETE CASCADE,

                        position INTEGER NOT NULL,

                        PRIMARY KEY (playlist_id, track_id)
);

CREATE INDEX idx_albums_user_id
    ON albums(user_id);

CREATE INDEX idx_playlists_user_id
    ON playlists(user_id);

CREATE INDEX idx_tracks_artist_id
    ON tracks(artist_id);

CREATE INDEX idx_tracks_album_id
    ON tracks(album_id);

CREATE INDEX idx_playlist_tracks_track_id
    ON playlist_tracks(track_id);

CREATE INDEX idx_tracks_format_id
    ON tracks(format_id);

CREATE INDEX idx_tracks_user_id
    ON tracks(user_id);

COMMIT;
