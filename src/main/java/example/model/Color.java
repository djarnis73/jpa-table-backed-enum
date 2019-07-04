package example.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Slf4j
@RequiredArgsConstructor
public enum Color {

    RED(255, 0, 0),
    GREEN(0, 255,0),
    BLUE(0,0, 255),
    ALMOND(0xef, 0xde, 0xcd)
    ;

    @Id
    public final byte id = (byte)ordinal();

    @Column(unique = true, nullable = false)
    public final String name = name();

    @Column(nullable = false)
    public final int red;

    @Column(nullable = false)
    public final int green;

    @Column(nullable = false)
    public final int blue;

}
