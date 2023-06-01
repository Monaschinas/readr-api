package com.monaschinas.readr.platform.profile.api.rest;

import com.monaschinas.readr.platform.profile.domain.service.ProfileService;
import com.monaschinas.readr.platform.profile.mapping.ProfileMapper;
import com.monaschinas.readr.platform.profile.resource.CreateProfileResource;
import com.monaschinas.readr.platform.profile.resource.ProfileResource;
import com.monaschinas.readr.platform.profile.resource.UpdateProfileResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = "application/json")
public class ProfileController {
  private final ProfileService profileService;
  private final ProfileMapper mapper;

  public ProfileController(ProfileService profileService, ProfileMapper mapper) {
    this.profileService = profileService;
    this.mapper = mapper;
  }

  @GetMapping
  public Page<ProfileResource> getAllProfiles(Pageable pageable) {
    return mapper.modelListPage(profileService.getAll(), pageable);
  }

  @GetMapping("{profileId}")
  public ProfileResource getProfileById(@PathVariable Long profileId) {
    return mapper.toResource(profileService.getById(profileId));
  }

  @PostMapping
  public ResponseEntity<ProfileResource> createProfile(CreateProfileResource resource) {
    return new ResponseEntity<>(mapper.toResource(profileService.create(mapper.toModel(resource))), HttpStatus.CREATED);
  }

  @PutMapping("{profileId}")
  public ProfileResource updateProfile(@PathVariable Long profileId, @RequestBody UpdateProfileResource resource) {
    return mapper.toResource(profileService.update(profileId, mapper.toModel(resource)));
  }

  @DeleteMapping("{profileId}")
  public ResponseEntity<?> deleteStudent(@PathVariable Long profileId) {
    return profileService.delete(profileId);
  }
}
