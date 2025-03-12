package imt.production.dev.Dto.MonstreUtils;

import imt.production.dev.Errors.HTTP_404.TypeElementaireNotFoundException;

public enum TypeElementaire {
    FEU, EAU, TERRE, AIR;

    public static TypeElementaire fromString(String type) {
        return switch (type.toLowerCase()) {
            case "fire" -> FEU;
            case "water" -> EAU;
            case "wind" -> AIR;
            case "earth" -> TERRE;
            default -> {
                throw new TypeElementaireNotFoundException("Cast TypeElementaire -> String: Type inconnu: " + type);
            }
        };
    }
}
