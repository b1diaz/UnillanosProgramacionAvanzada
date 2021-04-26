using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Spofy.Models;
using Spofy.Services;
using Spofy.ViewModels;

namespace Spofy.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CancionController : ControllerBase
    {
        #region ---------------- Dependencias  ---------------
        private readonly CancionServices _CancionService;

        public CancionController(CancionServices CancionServices)
        {
            this._CancionService = CancionServices;
        }
        #endregion

        /// <summary>
        /// Obtiene todas las canciones
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Authorize]
        public async Task<IActionResult> GetAll()
        {
            try
            {
                var lista = await _CancionService.GetAll();
                return Ok(lista);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        /// <summary>
        /// Crea una nueva cancion
        /// </summary>
        /// <param name="CancionViewModel"></param>
        /// <returns></returns>
        [HttpPost]
        public async Task<IActionResult> Post(CancionViewModel CancionViewModel)
        {
            try
            {
                var Cancion = new Cancion
                {
                    Nombre = CancionViewModel.Nombre,
                    AutorId = CancionViewModel.AutorId,
                    Genero = CancionViewModel.Genero
                };

                await _CancionService.Create(Cancion);
                return Ok("Autor creado correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }


        /// <summary>
        /// Actualiza una cancion
        /// </summary>
        /// <param name="Cancion"></param>
        /// <returns></returns>
        [HttpPut]
        public async Task<IActionResult> Update(Cancion Cancion)
        {
            try
            {
                await _CancionService.Update(Cancion);
                return Ok("Cancion actualizada correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        /// <summary>
        /// Elimina una cancion por su id
        /// </summary>
        /// <param name="CancionId"></param>
        /// <returns></returns>
        [HttpDelete]
        public async Task<IActionResult> Delete(long CancionId)
        {
            try
            {
                await _CancionService.Delete(CancionId);
                return Ok("Cancion Eliminada correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }


    }
}
