package com.tapanda.tapanda.service.Impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jcraft.jsch.ChannelSftp;
import com.tapanda.tapanda.component.FileStorageComponent;
import com.tapanda.tapanda.model.Image;
import com.tapanda.tapanda.properties.FileStorageProperties;
import com.tapanda.tapanda.requestDto.FileRequestDto;
import com.tapanda.tapanda.responseDto.FileResponseDto;
import com.tapanda.tapanda.service.FileStorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

	private final FileStorageComponent fileStorageComponent;

	private final FileStorageProperties config;

	@Value("${sftp.client.root}")
	private String imageFolder;

	@Override
	public FileRequestDto fileUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		UUID iid = UUID.randomUUID();
		String fileOriginName = file.getOriginalFilename();
		String fileName = iid + "_" + fileOriginName;
		try {
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot());
			sftp.put(file.getInputStream(), fileName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileStorageComponent.disconnect(sftp);
		}

		FileRequestDto dto = new FileRequestDto();
		dto.setIid(iid.toString());
		dto.setName(fileOriginName);
		dto.setUrl(fileName);
		return dto;

	}

	@Override
	public File fileDownload(FileResponseDto dto) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		OutputStream outputStream = null;
		File file = null;
		try {
			String fileName = dto.getIid() + "_" + dto.getName();
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot());
			file = new File(fileName);
			outputStream = new FileOutputStream(file);
			sftp.get(fileName, outputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileStorageComponent.disconnect(sftp);
		}
		return file;
	}
	
	@Override
	public byte[] userFileDownload(FileResponseDto dto) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		try {
			String fileName = dto.getIid() + "_" + dto.getName();
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot()  + "/user");
			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
				sftp.get(fileName, outputStream);
				return outputStream.toByteArray();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileStorageComponent.disconnect(sftp);
		}
		return null;
	}

	@Override
	public boolean fileDelete(FileResponseDto dto) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		try {
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot());
			sftp.rm(dto.getIid() + "_" + dto.getName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			fileStorageComponent.disconnect(sftp);
		}
	}

	@Override
	public FileRequestDto portfolioFileUpload(MultipartFile file) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		UUID iid = UUID.randomUUID();
		String fileOriginName = file.getOriginalFilename();
		String fileName = iid + "_" + fileOriginName;
		try {
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot() + "/portfolio");
			sftp.put(file.getInputStream(), fileName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileStorageComponent.disconnect(sftp);
		}

		FileRequestDto dto = new FileRequestDto();
		dto.setIid(iid.toString());
		dto.setName(fileOriginName);
		dto.setUrl(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/tapanda/portfolio/" + iid);
		return dto;

	}

	@Override
	public boolean portfolioFileDelete(Image im) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		try {
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot() + "/portfolio");
			sftp.rm(im.getIid().toString() + "_" + im.getName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			fileStorageComponent.disconnect(sftp);
		}
	}

	@Override
	public FileRequestDto userFileUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		UUID iid = UUID.randomUUID();
		String fileOriginName = file.getOriginalFilename();
		String fileName = iid + "_" + fileOriginName;
		try {
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot() + "/user");
			sftp.put(file.getInputStream(), fileName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileStorageComponent.disconnect(sftp);
		}

		FileRequestDto dto = new FileRequestDto();
		dto.setIid(iid.toString());
		dto.setName(fileOriginName);
		dto.setUrl(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/tapanda/user/" + iid);
		return dto;
	}

	@Override
	public boolean userFileDelete(Image im) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		try {
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot() + "/user");
			sftp.rm(im.getIid().toString() + "_" + im.getName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			fileStorageComponent.disconnect(sftp);
		}
	}

	@Override
	public byte[] portfolioFileDownload(FileResponseDto dto) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		try {
			String fileName = dto.getIid() + "_" + dto.getName();
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot()  + "/portfolio");
			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
				sftp.get(fileName, outputStream);
				return outputStream.toByteArray();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileStorageComponent.disconnect(sftp);
		}
		return null;
	}

	@Override
	public FileRequestDto productFileUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		ChannelSftp sftp = null;
		UUID iid = UUID.randomUUID();
		String fileOriginName = file.getOriginalFilename();
		String fileName = iid + "_" + fileOriginName;
		try {
			sftp = fileStorageComponent.createFtp();
			sftp.cd(config.getRoot() + "/portfolio");
			sftp.put(file.getInputStream(), fileName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileStorageComponent.disconnect(sftp);
		}

		FileRequestDto dto = new FileRequestDto();
		dto.setIid(iid.toString());
		dto.setName(fileOriginName);
		dto.setUrl(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/tapanda/portfolio/" + iid);
		return dto;

	}
}