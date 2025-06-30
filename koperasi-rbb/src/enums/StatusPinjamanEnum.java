/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author wtf
 */

public enum StatusPinjamanEnum {
    MENUNGGU("Menunggu"),
    DISETUJUI("Disetujui"),
    DITOLAK("Ditolak"),
    DIBATALKAN("Dibatalkan"),
    LUNAS("Lunas");
    
    private final String label;

    StatusPinjamanEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static StatusPinjamanEnum fromString(String status) {
        for (StatusPinjamanEnum s : StatusPinjamanEnum.values()) {
            if (s.label.equalsIgnoreCase(status) || s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null;
    }
}
