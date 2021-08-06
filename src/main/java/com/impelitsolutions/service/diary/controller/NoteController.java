package com.impelitsolutions.service.diary.controller;

import com.impelitsolutions.service.common.payload.ApiResponse;
import com.impelitsolutions.service.common.utils.AppConstants;
import com.impelitsolutions.service.diary.payload.*;
import com.impelitsolutions.service.diary.service.NoteInfoService;
import com.impelitsolutions.service.idm.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/note")
public class NoteController {

    public static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    NoteInfoService noteInfoService;

    @PostMapping("/category/submit")
    public ApiResponse addNoteCategory(@RequestBody NoteCategory request) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ApiResponse response = noteInfoService.addNoteCategory(request, userPrincipal.getId());
            return response;
        } catch (Throwable t) {
            LOGGER.error("ERROR Enrollment", t);
            return new ApiResponse(false, "Internal server error. Please contact with admin");
        }
    }

    @PostMapping("/info/submit")
    public ApiResponse addNoteInfo(@RequestBody NoteRegisterRequest request) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ApiResponse response = noteInfoService.addNote(request, userPrincipal.getId());
            return response;
        } catch (Throwable t) {
            LOGGER.error("ERROR Enrollment", t);
            return new ApiResponse(false, "Internal server error. Please contact with admin");
        }
    }

    @PostMapping("/info/delete")
    public ApiResponse deleteNoteInfo(@RequestBody NoteInfo request) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ApiResponse response = noteInfoService.deleteNoteInfo(request, userPrincipal.getId());
            return response;
        } catch (Throwable t) {
            LOGGER.error("ERROR Enrollment", t);
            return new ApiResponse(false, "Internal server error. Please contact with admin");
        }
    }

    @PostMapping("/category/list")
    public NoteCategoryResponse searchNoteCategory(@RequestBody NoteCategory criteria, HttpServletRequest request) {
        NoteCategoryResponse response = null;
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            response = noteInfoService.searchNoteCategory(criteria, userPrincipal.getId());
            response.setSuccess(true);
            response.setMessage(AppConstants.SUCCESS);
        } catch (Throwable t) {
            LOGGER.error("LOGIN ERROR:", t);
            return new NoteCategoryResponse(false, "Internal Server Error. Please try again later!");
        }
        return response;
    }

    @PostMapping("/list")
    public NoteInfoResponse searchNoteInfo(@RequestBody NoteSearchCriteria criteria, HttpServletRequest request) {
        NoteInfoResponse response = null;
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            response = noteInfoService.searchNoteInfo(criteria, userPrincipal.getId());
            response.setSuccess(true);
            response.setMessage(AppConstants.SUCCESS);
        } catch (Throwable t) {
            LOGGER.error("LOGIN ERROR:", t);
            return new NoteInfoResponse(false, "Internal Server Error. Please try again later!");
        }
        return response;
    }
}
