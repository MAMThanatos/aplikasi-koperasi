/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author wtf
 */
public enum StatusNasabahEnum {
    AKTIF("Aktif"),
    SUSPEND("Suspend");
    
    private final String label;

    StatusNasabahEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static StatusNasabahEnum fromString(String status) {
        for (StatusNasabahEnum s : StatusNasabahEnum.values()) {
            if (s.label.equalsIgnoreCase(status) || s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null;
    }
}
