/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author wtf
 */
public enum StatusAngsuranEnum {
    BELUM_LUNAS("Belum Lunas"),
    LUNAS("Lunas");
    
    private final String label;

    StatusAngsuranEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static StatusAngsuranEnum fromString(String status) {
        for (StatusAngsuranEnum s : StatusAngsuranEnum.values()) {
            if (s.label.equalsIgnoreCase(status) || s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null;
    }
}
