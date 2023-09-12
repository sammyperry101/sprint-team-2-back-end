INSERT INTO Capabilities(Name) VALUES("Engineering");
INSERT INTO Capabilities(Name) VALUES("Platforms");
INSERT INTO Capabilities(Name) VALUES("Data and Artificial Intelligence");
INSERT INTO Capabilities(Name) VALUES("Cyber Security");
INSERT INTO Capabilities(Name) VALUES("Workday");
INSERT INTO Capabilities(Name) VALUES("Experience Design");
INSERT INTO Capabilities(Name) VALUES("Product");
INSERT INTO Capabilities(Name) VALUES("Delivery");
INSERT INTO Capabilities(Name) VALUES("Operations");
INSERT INTO Capabilities(Name) VALUES("Business Development and Marketing");
INSERT INTO Capabilities(Name) VALUES("Organisational Strategy and Planning");
INSERT INTO Capabilities(Name) VALUES("People");
INSERT INTO Capabilities(Name) VALUES("Commercial and Financial Management");
INSERT INTO Capabilities(Name) VALUES("Business Services Support");

INSERT INTO Families(Name, CapabilityID) VALUES("Engineering Strategy and Planning", 1);
INSERT INTO Families(Name, CapabilityID) VALUES("Engineering", 1);
INSERT INTO Families(Name, CapabilityID) VALUES("Architecture", 1);
INSERT INTO Families(Name, CapabilityID) VALUES("Testing and Quality Assurance", 1);
INSERT INTO Families(Name, CapabilityID) VALUES("Product Specialist", 1);
INSERT INTO Families(Name, CapabilityID) VALUES("Platform Strategy and Planning", 2);
INSERT INTO Families(Name, CapabilityID) VALUES("Platform Engineering", 2);
INSERT INTO Families(Name, CapabilityID) VALUES("Platform Architecture", 2);
INSERT INTO Families(Name, CapabilityID) VALUES("Platform Specialists", 2);
INSERT INTO Families(Name, CapabilityID) VALUES("Cloud Migration", 2);
INSERT INTO Families(Name, CapabilityID) VALUES("Systems Support", 2);
INSERT INTO Families(Name, CapabilityID) VALUES("Data Engineering", 3);
INSERT INTO Families(Name, CapabilityID) VALUES("Data Architecture", 3);
INSERT INTO Families(Name, CapabilityID) VALUES("Artificial Intelligence (AI) Engineering", 3);
INSERT INTO Families(Name, CapabilityID) VALUES("Data Science", 3);
INSERT INTO Families(Name, CapabilityID) VALUES("Data Consulting", 3);
INSERT INTO Families(Name, CapabilityID) VALUES("Data Analytics", 3);
INSERT INTO Families(Name, CapabilityID) VALUES("Security Strategy and Planning", 4);
INSERT INTO Families(Name, CapabilityID) VALUES("Security Engineering", 4);
INSERT INTO Families(Name, CapabilityID) VALUES("Corporate Security", 4);
INSERT INTO Families(Name, CapabilityID) VALUES("Strategy and Planning", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("HCM", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Financials", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Data", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Integrations", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Change & User Adoption", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Adaptive Planning", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Product Services", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Product Development", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Extend", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Spend Management", 5);
INSERT INTO Families(Name, CapabilityID) VALUES("Insights", 6);
INSERT INTO Families(Name, CapabilityID) VALUES("Content Design", 6);
INSERT INTO Families(Name, CapabilityID) VALUES("UX / Visual Design", 6);
INSERT INTO Families(Name, CapabilityID) VALUES("Service Design", 6);
INSERT INTO Families(Name, CapabilityID) VALUES("Product Consultancy", 7);
INSERT INTO Families(Name, CapabilityID) VALUES("Digital Advisory Consultancy", 7);
INSERT INTO Families(Name, CapabilityID) VALUES("Low Code Consultancy", 7);
INSERT INTO Families(Name, CapabilityID) VALUES("Delivery Strategy & Planning", 8);
INSERT INTO Families(Name, CapabilityID) VALUES("Delivery Management", 8);
INSERT INTO Families(Name, CapabilityID) VALUES("Engineering Management", 8);
INSERT INTO Families(Name, CapabilityID) VALUES("Service Management", 8);
INSERT INTO Families(Name, CapabilityID) VALUES("Workday Engagement Management", 8);
INSERT INTO Families(Name, CapabilityID) VALUES("Operations Strategy and Planning", 9);
INSERT INTO Families(Name, CapabilityID) VALUES("Operational Management", 9);
INSERT INTO Families(Name, CapabilityID) VALUES("Staff Management", 9);
INSERT INTO Families(Name, CapabilityID) VALUES("Programme Management Office (PMO)", 9);
INSERT INTO Families(Name, CapabilityID) VALUES("Strategy and Planning", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Business Development", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Client Management", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Partners", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Bid Production", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Inside Sales", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Marketing-Business", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Marketing-Martech", 10);
INSERT INTO Families(Name, CapabilityID) VALUES("Organisational Strategy & Planning", 11);
INSERT INTO Families(Name, CapabilityID) VALUES("People Strategy and Planning", 12);
INSERT INTO Families(Name, CapabilityID) VALUES("People Operations / Strategic Partnering", 12);
INSERT INTO Families(Name, CapabilityID) VALUES("Engagement, Culture and Development", 12);
INSERT INTO Families(Name, CapabilityID) VALUES("Talent Acquisition", 12);
INSERT INTO Families(Name, CapabilityID) VALUES("Commerical and Financial Management Strategy & Planning", 13);
INSERT INTO Families(Name, CapabilityID) VALUES("Commercial", 13);
INSERT INTO Families(Name, CapabilityID) VALUES("Financial", 13);
INSERT INTO Families(Name, CapabilityID) VALUES("Payroll", 13);
INSERT INTO Families(Name, CapabilityID) VALUES("Business Services", 14);
INSERT INTO Families(Name, CapabilityID) VALUES("Property Management", 14);
INSERT INTO Families(Name, CapabilityID) VALUES("Travel Management", 14);
INSERT INTO Families(Name, CapabilityID) VALUES("Corporate Social Responsibility Management", 14);

INSERT INTO Bands(Name, Level) VALUES("Leadership Community", 0);
INSERT INTO Bands(Name, Level) VALUES("Principal", 1);
INSERT INTO Bands(Name, Level) VALUES("Manager", 2);
INSERT INTO Bands(Name, Level) VALUES("Consultant", 3);
INSERT INTO Bands(Name, Level) VALUES("Senior Associate", 4);
INSERT INTO Bands(Name, Level) VALUES("Associate", 5);
INSERT INTO Bands(Name, Level) VALUES("Trainee", 6);
INSERT INTO Bands(Name, Level) VALUES("Apprentice", 7);

INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);

INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Technology Leader",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EbVmcbCjUW9GnuKIvWQl4ZEBu1OexK_ZLriei8AUoRozCg?e=pkpCiw",
1, 1);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Principal Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EVF5r86T24NNpDy0weP5HdIBaCNWkL9__IzJXLHLzlpP4w?e=tsnXuj",
2, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Principal Test Architect",
"temp", "temp", "Principal Test Architect",
2, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Dynamics 365 / Power Platform Solution Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EeDnnrtxghNNgbBQixu5Hw0BdAC7nQddZQZV6sMPQnUoRg?e=HpPNNC",
3, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Solution Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/ESpp56fZyrhKi6uCuwpshDwB3nmo1BMkbQKYDXbNHCi_sw?e=muiHBJ",
3, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Test Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EaUc6UK0VK9EkoQGonvGc7gBFq5BA44lWgyImt5s-810BA?e=oL7L2S",
3, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Intelligent Automation Solution Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20Profile%20-%20Intelligent%20Automation%20Solution%20Architect%20(M).pdf?csf=1&web=1&e=0Nt9EZ",
3, 5);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Innovation
Lead",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EfDowM36RnBLtOSfM1n7ydsBrEhf4SrE_TGzS9ej432Cew?e=NCBKGp",
4, 1);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Lead Software Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EZwjG2_68rZOiWicTIbaM7IBGNimvPpVhz3Z8iyefkmDQw?e=fIh91B",
4, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Lead Managed Services Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EZURQr9PhANKgW_hsp4n47QBmjdD2in8Gz6Qi0R-62U7Bg?e=rGt6xD",
4, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Technical Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/ERa52ZFdOhxJuKYbwl5w27YBUGgHxFh6qz9AtTflB2YPGA?e=mMeOWH",
4, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Lead Test Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20-%20Lead%20Test%20Engineer%20(Consultant).pdf?csf=1&web=1&e=uzHzAa",
4, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Lead NFT Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EZb2TmMB0MBLlwWgFg4DLQ4B6gTWJbPbfzQqOqFTiEGWPA?e=lZxf1S",
4, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Test Manager",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EWhQmoHJz6lDmruVQbXPukwBdORoWzoD5m-2AqEFkk7sJA?e=3L2xrU",
4, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Lead Product
Specialist",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EaiJVYASE4FHucsGvPY2t7IB9F-8yVVaCO--dBpBhCHubw?e=cyvAnW",
4, 5);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior
Software Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EeS73plMEQ9Fjf-tovttXuYBEo-0tlfT-U3bFMcgryapVw?e=wJFypU",
5, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior Front-End Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/s/PeopleTeam-SharedDrive/Eatbo8zxX6tAtmbP8V1zp40B6IOOUSbmNXegGn_a9ooPVA?e=ozka8V",
5, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior Support Technician",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20Profile%20-%20Senior%20Support%20Technician%20(SA).pdf?csf=1&web=1&e=2bIVHA",
5, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior Test Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20-%20Senior%20Test%20Engineer%20(SA).pdf?csf=1&web=1&e=fhtv1A",
5, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior NFT Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EcXYPU9ji3FGqThWcoPDzUgBMsRkQiRqaTvPoTgIT5VAgw?e=KIgOFw",
5, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior Product
Specialist",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/ERcJ7fgNODVBnTXPuYBH2pQB68ij729Vzb63GI7BEtzvhg?e=yRso1j",
5, 5);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Software Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EYTCv1ssl6pOuH59zXtoF9YB8qNaEMNSkZIkCthDAY5Kjg?e=Ht84rW",
6, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Front-End Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/s/PeopleTeam-SharedDrive/EX743Ml-LgpIgMy2NPsaMSQBWezle54-hIhKKJQVJJ3Hcw?e=xQfVue",
6, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Support Technician ",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20Profile%20-%20Support%20Technician%20(As).pdf?csf=1&web=1&e=2ZNeHK",
6, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Test Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20%20Test%20Engineer%20(Associate).pdf?csf=1&web=1&e=vhB9vK",
6, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Product Specialist",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EfeMkAfUpYlDuqvBpgndb8EB5o___Gv8q5UKl7eNqDF8Ug?e=zR5zGI",
6, 5);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Software Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/ERJlfMHOxRVHlMHiUcHDkI8BU2NafboSbetIJ3zNkKwTlw?e=jOY3Vq",
7, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Test Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20Trainee%20Test%20Engineer%20(Trainee).pdf?csf=1&web=1&e=YW0Qo6",
7, 4);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Software Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EbTM1UOLa0VBvOttkOL3ZNoB0sMjehxvkAaNQEj2dqKMbA?e=iXpeUf",
8, 2);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Head of Cloud Practice",
"temp", "temp", "",
1, 6);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Principal Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EUEaCumLhexMqe_9rYGIFTcBTo5HQ32RMCwkCXotBkbUMA?e=c2bOQH",
2, 8);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Head of IT Operations",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/Ecj70_4gNTNPmf1UBZrQ15MB5k0YT5Um3L69uZ6Lm5V3MQ?e=7gCR4g",
2, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Solution Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EZmu38Tgcx1Ck0xdTo1pTDsBEU3YfPpm3aYvJDDKuBcICA?e=6jPUBe",
3, 8);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior Technical Specialist",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EZbkqO0Cd99HtJC_3_wwnysBZ9YjVzeN-aWjjn7aiT4NTw?e=DLdQbI",
3, 9);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Cloud Economist",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EQojt9-Y-zNFrkaNbluG2qUBoYEQcWUTfS8X_2Zo74mnTQ?e=leATPK",
3, 9);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Cloud Migration Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Platforms/Job%20profile%20-%20Cloud%20Migration%20Architect%20(Manager).pdf?csf=1&web=1&e=3PyRkh",
3, 10);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Systems Operations Manager",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Platforms/Job%20Profile%20-%20Systems%20Operations%20Manager%20(M).pdf?csf=1&web=1&e=ypSbmm",
3, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Lead Platform Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EawkJAUOlXtKrsijZD1If8QBZdkHPexQa9xx-RPvLpjx9Q?e=OKp8M1",
4, 7);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Technical Architect",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/Ecg28qe5QgBOiUZU0ksswvkBLejYuXBiYnbPMfGBc2RGEQ?e=bj6VRp",
4, 8);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Technical Specialist",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/ERs73RaRfnNLsOBMYCeZ-J8BaB1aRWSalUmkC4R32qZJDw?e=JRZBT8",
4, 9);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Cloud Migration Lead",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EYKMD_JZvhRJh7SB9CYxIhIBZncYIdjyIdr4Klyby2jdcw?e=mTlgf7",
4, 10);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Service Category Lead",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/ESWJ50QxadRMk_To583jmlIBkXgHbk89C-DiUPV3rkIVEA?e=0ZGoyw",
4, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Lead Systems Support Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/ESOVNkXvwgxPiln-o4JzuJ4BwnxC2BCUL5eR1GqLRkL94Q?e=ejKd4t",
4, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior Platform Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EQnPQ8NzxulCj-opU2QVe8UBtItlrDw4YY0v5HTNnrOAbA?e=jOcehE",
5, 7);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Service Category Lead",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EXGfwvEHPx9MmB-oz--OXbwBkm629zQ2Ax0YkpVqBM02Bg?e=er7VDe",
5, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Platform Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EWSXKSjhwnxLrgnVbdbEVzMBvVEcnd85n_JNzparaRtb6w?e=n3NH8a",
6, 7);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Systems Support Engineer ",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EQmCQe56FM5Hrbc89VdMKsYB0yfUXytB2gCK1E4muTQk4g",
6, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Trainee Platform Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EfUAWGv0l_RPoK9lsCiDJ0cBPxdxOQyunGoQCGFCU4zv-A?e=bo9Hg9",
7, 7);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Trainee Systems Support Engineer",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/Ed5VklXGwtpAvQN_Oy1MgUYBoL58Ee8vv3rOWHjpcdc4wQ?e=1HqMpS",
7, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("Senior Systems Support Engineer ",
"temp", "temp", "https://kainossoftwareltd.sharepoint.com/:b:/g/people/EcFdYkCtSbRNtSSw50j9PtIBuhIP7K_7Qav4et3_t7Q-Mg",
5, 11);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);
INSERT INTO Job_Roles(Name, Job_Spec, Responsibilities, Sharepoint_Link, BandID, FamilyID) VALUES ("",
"temp", "temp", "",
1, 3);





