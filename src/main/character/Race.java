package main.character;
/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Race {
    ELF("Elf"),
    HUMAN("Human"),
    ORC("Orc"),
    UNDEAD("Undead"),
    DWARF("Dwarf");
    
    private String m_name;

    Race(String name) {
        m_name= name;
    }
    
    public String toString()
    {
        return m_name;
    }
}
