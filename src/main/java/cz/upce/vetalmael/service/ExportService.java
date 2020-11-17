package cz.upce.vetalmael.service;

import java.io.ByteArrayInputStream;

public interface ExportService {

    ByteArrayInputStream exportToPdf(int idReport);
}
